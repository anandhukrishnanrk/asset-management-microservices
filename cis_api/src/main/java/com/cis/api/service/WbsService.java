package com.cis.api.service;

import com.cis.api.entity.ActivityType;
import com.cis.api.entity.ProjectDetails;
import com.cis.api.entity.SetupDeliverableFrequency;
import com.cis.api.entity.WBSSetup;
import com.cis.api.repository.ActivityTypeRepository;
import com.cis.api.repository.ComponentTypeRepository;
import com.cis.api.repository.SetupDeliverableFrequencyRepository;
import com.cis.api.repository.SetupDeliverableTypeRepository;
import com.cis.api.repository.WBSSetupRepository;
import com.cis.api.repository.WbsRepository;
import com.cis.api.repository.WBSVersionRepository;
import com.cis.api.entity.WBSVersion;
import com.cis.api.vo.WbsComponentVo;
import com.cis.api.vo.WbsGroupVo;
import com.cis.api.vo.WbsResponseVo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.cis.api.entity.ComponentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

@Service
public class WbsService {

        private static final int AUDIT_GROUP_ID = 11;

        private static final Logger logger = LoggerFactory.getLogger(WbsService.class);

        @Autowired
        private WbsRepository wbsRepository;

        @Autowired
        private WBSSetupRepository wbsSetupRepository;

        @Autowired
        private EntityManager entityManager;

        @Autowired
        private WBSVersionRepository wbsVersionRepository;

        @Autowired
        private ComponentTypeRepository componentTypeRepository;

        @Autowired
        private ActivityTypeRepository activityTypeRepository;

        @Autowired
        private SetupDeliverableTypeRepository setupDeliverableTypeRepository;

        @Autowired
        private SetupDeliverableFrequencyRepository setupDeliverableFrequencyRepository;

        /**
         * read-only and returns a structured VO instead of setting request attributes.
         *
         * @param cmmiProjectId the CMMI project row id
         * @param moduleId      0 or null = all modules
         */
        public WbsResponseVo getWbsView(Integer projectId, Integer moduleId) {

                if (moduleId == null)
                        moduleId = 0;

                WbsResponseVo response = new WbsResponseVo();
                response.setProjectId(projectId);
                response.setModuleId(moduleId);

                Integer cmmiProjectId = wbsRepository.getCmmiProjectIdByProjectId(projectId);
                response.setCmmiProjectId(cmmiProjectId);
                response.setProjectName(wbsRepository.getProjectNameByCmmiProjectId(cmmiProjectId));

                // Return empty response when cmmiProject doesn't exist
                if (projectId == null) {
                        response.setWbsGroups(new ArrayList<>());
                        response.setComponents(new ArrayList<>());
                        return response;
                }

                // ── Effort / date summary ───
                response.setHoursWorkedOnCoding(wbsRepository.getTotalHoursWorkedOnCoding(projectId, moduleId));
                response.setHoursWorkedOnBug(wbsRepository.getTotalHoursWorkedOnBug(projectId, moduleId));
                response.setHoursWorkedOnDefect(wbsRepository.getTotalHoursWorkedOnDefect(projectId, moduleId));

                response.setCodingStartDate(wbsRepository.getCodingStartDate(projectId, moduleId));
                response.setCodingEndDate(wbsRepository.getCodingEndDate(projectId, moduleId));
                response.setBugStartDate(wbsRepository.getBugStartDate(projectId));
                response.setBugEndDate(wbsRepository.getBugEndDate(projectId));
                response.setDefectStartDate(wbsRepository.getDefectStartDate(projectId, moduleId));
                response.setDefectEndDate(wbsRepository.getDefectEndDate(projectId, moduleId));

                // ── Raw components ─────
                List<WbsComponentVo> allComponents = wbsRepository.getProjectComponents(cmmiProjectId, moduleId);

                if (allComponents == null)
                        allComponents = new ArrayList<>();

                // ── Filter audit components (group_id = 11) ────
                List<Integer> assignedAuditIds = wbsRepository.getAssignedAuditIdsByProjectId(projectId);

                final int finalModuleId = moduleId;
                List<WbsComponentVo> filteredComponents = allComponents.stream()
                                .filter(comp -> {
                                        if (comp.getGroupId() != null && comp.getGroupId() == AUDIT_GROUP_ID) {
                                                // Keep audit component only when its audit is assigned to this project
                                                return comp.getAuditId() != null
                                                                && assignedAuditIds.contains(comp.getAuditId());
                                        }
                                        return true; // Always keep non-audit components
                                })
                                .collect(Collectors.toList());
                // response.setComponents(filteredComponents);

                // ── Groups with summary dates/effort ────────
                List<WbsGroupVo> groups = wbsRepository.getWbsGroups(moduleId);
                for (WbsGroupVo group : groups) {
                        group.setPlannedStartDate(
                                        wbsRepository.getGroupPlannedStartDate(cmmiProjectId, group.getId(),
                                                        finalModuleId));
                        group.setPlannedEndDate(
                                        wbsRepository.getGroupPlannedEndDate(cmmiProjectId, group.getId(),
                                                        finalModuleId));
                        group.setPlannedEffort(
                                        wbsRepository.getGroupPlannedEffort(cmmiProjectId, group.getId(),
                                                        finalModuleId));
                        group.setActualStartDate(
                                        wbsRepository.getGroupActualStartDate(cmmiProjectId, group.getId(),
                                                        finalModuleId));
                        group.setActualEndDate(
                                        wbsRepository.getGroupActualEndDate(cmmiProjectId, group.getId(),
                                                        finalModuleId));
                        group.setActualEffort(
                                        wbsRepository.getGroupActualEffort(cmmiProjectId, group.getId(),
                                                        finalModuleId));

                        // Attach the filtered components that belong to this group
                        List<WbsComponentVo> groupComponents = filteredComponents.stream()
                                        .filter(c -> group.getId().equals(c.getGroupId()))
                                        .collect(Collectors.toList());
                        group.setComponents(groupComponents);
                }

                response.setWbsGroups(groups);

                // ── Totals ───────────────────────────────────────────────────────────
                double totalPlanned = filteredComponents.stream()
                                .filter(c -> c.getPlannedEffort() != null)
                                .mapToDouble(WbsComponentVo::getPlannedEffort)
                                .sum();
                double totalActual = filteredComponents.stream()
                                .filter(c -> c.getActualEffort() != null)
                                .mapToDouble(WbsComponentVo::getActualEffort)
                                .sum();

                response.setTotalPlannedEffort(totalPlanned);
                response.setTotalActualEffort(totalActual);

                return response;
        }

        public WBSVersion saveWbsVersion(Long projectId, String versionName, String versionNumber, String description,
                        String createdBy) {
                WBSVersion version = new WBSVersion();

                ProjectDetails pd = new ProjectDetails();
                pd.setProjectId(projectId);
                version.setProject(pd);

                version.setVersionName(versionName);
                version.setVersionNumber(versionNumber);
                version.setWbsDescription(description);
                version.setCreatedBy(createdBy);
                version.setCreatedOn(new Date());
                version.setActiveStatus("Y");

                return wbsVersionRepository.save(version);
        }

        public List<WBSVersion> getWbsVersionsByProjectId(Long projectId) {
                return wbsVersionRepository.findByProjectProjectId(projectId);
        }

        // @Override
        // public ByteArrayOutputStream generateWBSExcelByProjectId(Long projectId)
        // throws IOException {
        // return generateWBSExcelByProjectId(projectId, null, null, null, null, null);
        // }

        public ByteArrayOutputStream generateWBSExcelByProjectId(Long projectId, String wbsType, Long activityTypeId,
                        String activityStatus, String deliverable, String billable) throws IOException {
                if (projectId == null) {
                        throw new IllegalArgumentException("Project ID is required");
                }
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Work Breakdown Structure");

                // Create header style
                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 12);
                headerStyle.setFont(headerFont);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                headerStyle.setFillForegroundColor(
                                org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                // Add borders
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);

                // Create data style
                CellStyle dataStyle = workbook.createCellStyle();
                dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                // Add borders
                dataStyle.setBorderTop(BorderStyle.THIN);
                dataStyle.setBorderBottom(BorderStyle.THIN);
                dataStyle.setBorderLeft(BorderStyle.THIN);
                dataStyle.setBorderRight(BorderStyle.THIN);

                // Create component style (bold, light blue background)
                CellStyle componentStyle = workbook.createCellStyle();
                Font componentFont = workbook.createFont();
                componentFont.setBold(true);
                componentFont.setFontHeightInPoints((short) 11);
                componentStyle.setFont(componentFont);
                componentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                componentStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.PALE_BLUE.getIndex());
                componentStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                // Add borders
                componentStyle.setBorderTop(BorderStyle.THIN);
                componentStyle.setBorderBottom(BorderStyle.THIN);
                componentStyle.setBorderLeft(BorderStyle.THIN);
                componentStyle.setBorderRight(BorderStyle.THIN);

                // Create activity style (normal, light gray background)
                CellStyle activityStyle = workbook.createCellStyle();
                activityStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                activityStyle.setFillForegroundColor(
                                org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
                activityStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                // Add borders
                activityStyle.setBorderTop(BorderStyle.THIN);
                activityStyle.setBorderBottom(BorderStyle.THIN);
                activityStyle.setBorderLeft(BorderStyle.THIN);
                activityStyle.setBorderRight(BorderStyle.THIN);

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = { "Code", "Description", "Component/Activity Type", "Start Date", "Due Date",
                                "Status", "Priority", "Progress (%)", "Planned Hours", "Actual Hours", "Assigned To",
                                "Is Deliverable", "Client Approval Needed", "Deliverable Type", "Deliverable Frequency",
                                "Is Billable", "Billable Amount", "Tax Rate (%)", "Billable Amount (Inc. Tax)" };
                for (int i = 0; i < headers.length; i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(headers[i]);
                        cell.setCellStyle(headerStyle);
                }

                // Get WBS tree data for project with filters
                // For Excel report: only show active items
                List<WBSSetup> tree = buildFilteredActiveWBSTreeByProjectId(projectId, wbsType, activityTypeId,
                                activityStatus, deliverable, billable);
                int rowNum = 1;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Write tree data recursively
                for (WBSSetup node : tree) {
                        rowNum = writeWBSNodeToExcel(sheet, node, rowNum, 0, dateFormat, dataStyle);
                }

                // Auto-size columns
                for (int i = 0; i < headers.length; i++) {
                        sheet.autoSizeColumn(i);
                }

                // Set specific widths for Start Date and Due Date columns (columns 3 and 4) -
                // half of previous width
                sheet.setColumnWidth(3, 8 * 256); // Start Date - 8 characters wide (half of 15)
                sheet.setColumnWidth(4, 8 * 256); // Due Date - 8 characters wide (half of 15)

                // Write to ByteArrayOutputStream
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                workbook.close();

                return outputStream;
        }

        private int writeWBSNodeToExcel(Sheet sheet, WBSSetup node, int rowNum, int indentLevel,
                        SimpleDateFormat dateFormat, CellStyle dataStyle) {
                Row row = sheet.createRow(rowNum);

                // Determine if this is a component or activity
                boolean isComponent = (node.getWbsType() != null && "COMPONENT".equals(node.getWbsType())) ||
                                (node.getLevel() != null && node.getLevel() == 0);

                // Create component style (bold, light blue background)
                CellStyle componentStyle = sheet.getWorkbook().createCellStyle();
                Font componentFont = sheet.getWorkbook().createFont();
                componentFont.setBold(true);
                componentFont.setFontHeightInPoints((short) 11);
                componentStyle.setFont(componentFont);
                componentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                componentStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.PALE_BLUE.getIndex());
                componentStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                // Add borders
                componentStyle.setBorderTop(BorderStyle.THIN);
                componentStyle.setBorderBottom(BorderStyle.THIN);
                componentStyle.setBorderLeft(BorderStyle.THIN);
                componentStyle.setBorderRight(BorderStyle.THIN);

                // Create activity style (normal, #f8f8f8 background - RGB: 248, 248, 248)
                CellStyle activityStyle = sheet.getWorkbook().createCellStyle();
                activityStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                // Use custom color #f8f8f8 (RGB: 248, 248, 248)
                if (sheet.getWorkbook() instanceof org.apache.poi.xssf.usermodel.XSSFWorkbook) {
                        org.apache.poi.xssf.usermodel.XSSFColor activityColor = new org.apache.poi.xssf.usermodel.XSSFColor(
                                        new java.awt.Color(248, 248, 248),
                                        ((org.apache.poi.xssf.usermodel.XSSFWorkbook) sheet.getWorkbook())
                                                        .getStylesSource()
                                                        .getIndexedColors());
                        ((org.apache.poi.xssf.usermodel.XSSFCellStyle) activityStyle)
                                        .setFillForegroundColor(activityColor);
                } else {
                        // Fallback for HSSF (older Excel format)
                        activityStyle.setFillForegroundColor(
                                        org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
                }
                activityStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                // Add borders
                activityStyle.setBorderTop(BorderStyle.THIN);
                activityStyle.setBorderBottom(BorderStyle.THIN);
                activityStyle.setBorderLeft(BorderStyle.THIN);
                activityStyle.setBorderRight(BorderStyle.THIN);

                CellStyle currentStyle = isComponent ? componentStyle : activityStyle;

                // Indent based on level
                String indent = "";
                for (int i = 0; i < indentLevel; i++) {
                        indent += "  ";
                }

                // Code
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(indent + (node.getWbsCode() != null ? node.getWbsCode() : ""));
                cell0.setCellStyle(currentStyle);

                // Description
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(node.getDescription() != null ? node.getDescription() : "");
                cell1.setCellStyle(currentStyle);

                // Component/Activity Type (from type tables)
                Cell cell2 = row.createCell(2);
                String typeName = "";
                if (isComponent && node.getComponentType() != null) {
                        typeName = node.getComponentType().getTypeName() != null ? node.getComponentType().getTypeName()
                                        : "";
                } else if (!isComponent && node.getActivityType() != null) {
                        typeName = node.getActivityType().getTypeName() != null ? node.getActivityType().getTypeName()
                                        : "";
                }
                cell2.setCellValue(typeName);
                cell2.setCellStyle(currentStyle);

                // Start Date (with center alignment)
                CellStyle dateStyle = sheet.getWorkbook().createCellStyle();
                dateStyle.cloneStyleFrom(currentStyle);
                dateStyle.setAlignment(HorizontalAlignment.CENTER);
                Cell cell3 = row.createCell(3);
                if (node.getStartDate() != null) {
                        cell3.setCellValue(dateFormat.format(node.getStartDate()));
                }
                cell3.setCellStyle(dateStyle);

                // Due Date (with center alignment)
                Cell cell4 = row.createCell(4);
                if (node.getDueDate() != null) {
                        cell4.setCellValue(dateFormat.format(node.getDueDate()));
                }
                cell4.setCellStyle(dateStyle);

                // Status
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(node.getStatus() != null ? node.getStatus() : "");
                cell5.setCellStyle(currentStyle);

                // Priority
                Cell cell6 = row.createCell(6);
                cell6.setCellValue(node.getPriority() != null ? node.getPriority() : "");
                cell6.setCellStyle(currentStyle);

                // Progress
                Cell cell7 = row.createCell(7);
                if (node.getProgress() != null) {
                        cell7.setCellValue(node.getProgress());
                }
                cell7.setCellStyle(currentStyle);

                // Planned Hours
                Cell cell8 = row.createCell(8);
                if (node.getEstimatedHours() != null) {
                        cell8.setCellValue(node.getEstimatedHours());
                }
                cell8.setCellStyle(currentStyle);

                // Actual Hours
                Cell cell9 = row.createCell(9);
                if (node.getActualHours() != null) {
                        cell9.setCellValue(node.getActualHours());
                }
                cell9.setCellStyle(currentStyle);

                // Assigned To
                Cell cell10 = row.createCell(10);
                // TODO: Implement assigned to
                // if (node.getAssignedTo() != null && node.getAssignedTo().getDisplayName() !=
                // null) {
                // cell10.setCellValue(node.getAssignedTo().getDisplayName());
                // }
                cell10.setCellStyle(currentStyle);

                // Is Deliverable
                Cell cell11 = row.createCell(11);
                cell11.setCellValue(node.getIsDeliverable() != null ? node.getIsDeliverable() : "");
                cell11.setCellStyle(currentStyle);

                // Client Approval Needed
                Cell cell12 = row.createCell(12);
                cell12.setCellValue(node.getClientApprovalNeeded() != null ? node.getClientApprovalNeeded() : "");
                cell12.setCellStyle(currentStyle);

                // Deliverable Type
                Cell cell13 = row.createCell(13);
                String deliverableTypeStr = "";
                if (node.getDeliverableType() != null && node.getDeliverableType().getDeliverableType() != null) {
                        deliverableTypeStr = node.getDeliverableType().getDeliverableType();
                }
                cell13.setCellValue(deliverableTypeStr);
                cell13.setCellStyle(currentStyle);

                // Deliverable Frequency
                Cell cell14 = row.createCell(14);
                String deliverableFreqStr = "";
                if (node.getDeliverableFrequency() != null
                                && node.getDeliverableFrequency().getFrequencyType() != null) {
                        deliverableFreqStr = node.getDeliverableFrequency().getFrequencyType();
                }
                cell14.setCellValue(deliverableFreqStr);
                cell14.setCellStyle(currentStyle);

                // Is Billable
                Cell cell15 = row.createCell(15);
                cell15.setCellValue(node.getIsBillable() != null ? node.getIsBillable() : "");
                cell15.setCellStyle(currentStyle);

                // Billable Amount
                Cell cell16 = row.createCell(16);
                if (node.getBillableAmount() != null) {
                        cell16.setCellValue(node.getBillableAmount());
                }
                cell16.setCellStyle(currentStyle);

                // Tax Rate (%)
                Cell cell17 = row.createCell(17);
                if (node.getTaxRate() != null) {
                        cell17.setCellValue(node.getTaxRate());
                }
                cell17.setCellStyle(currentStyle);

                // Billable Amount (Inc. Tax)
                Cell cell18 = row.createCell(18);
                if (node.getBillableAmountIncTax() != null) {
                        cell18.setCellValue(node.getBillableAmountIncTax());
                }
                cell18.setCellStyle(currentStyle);

                rowNum++;

                // Write children recursively
                if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                        for (WBSSetup child : node.getChildren()) {
                                rowNum = writeWBSNodeToExcel(sheet, child, rowNum, indentLevel + 1, dateFormat,
                                                currentStyle);
                        }
                }

                return rowNum;
        }

        public List<WBSSetup> buildFilteredActiveWBSTreeByProjectId(Long projectId, String wbsType,
                        Long activityTypeId, String activityStatus, String deliverable, String billable) {
                List<WBSSetup> rootWBS = wbsSetupRepository.findRootWBSByProjectId(projectId);
                List<WBSSetup> filteredRootWBS = new java.util.ArrayList<>();

                for (WBSSetup wbs : rootWBS) {
                        // Build children first
                        buildChildrenByProject(wbs, projectId);

                        // Filter the tree recursively
                        WBSSetup filteredWBS = filterWBSTree(wbs, wbsType, activityTypeId, activityStatus, deliverable,
                                        billable);
                        if (filteredWBS != null) {
                                filteredRootWBS.add(filteredWBS);
                        }
                }
                return filteredRootWBS;
        }

        /**
         * Recursively filter WBS tree based on criteria
         */
        private WBSSetup filterWBSTree(WBSSetup node, String wbsType, Long activityTypeId, String activityStatus,
                        String deliverable, String billable) {
                // Check if node matches filters
                boolean matches = true;

                // Filter by WBS Type
                if (wbsType != null && !wbsType.isEmpty() && !"BOTH".equals(wbsType)) {
                        if (node.getWbsType() == null || !wbsType.equals(node.getWbsType())) {
                                matches = false;
                        }
                }

                // Filter by Activity Type (only for ACTIVITY and SUB_ACTIVITY)
                if (matches && activityTypeId != null && node.getActivityType() != null) {
                        if (!activityTypeId.equals(node.getActivityType().getId())) {
                                matches = false;
                        }
                }

                // Filter by Activity Status (only for ACTIVITY and SUB_ACTIVITY)
                if (matches && activityStatus != null && !activityStatus.isEmpty()) {
                        if (node.getStatus() == null || !activityStatus.equals(node.getStatus())) {
                                matches = false;
                        }
                }

                // Filter by Deliverable
                if (matches && deliverable != null && !deliverable.isEmpty()) {
                        String nodeDeliverable = node.getIsDeliverable() != null ? node.getIsDeliverable() : "N";
                        if (!deliverable.equals(nodeDeliverable)) {
                                matches = false;
                        }
                }

                // Filter by Billable
                if (matches && billable != null && !billable.isEmpty()) {
                        String nodeBillable = node.getIsBillable() != null ? node.getIsBillable() : "N";
                        if (!billable.equals(nodeBillable)) {
                                matches = false;
                        }
                }

                // If node doesn't match, check if any children match
                if (!matches && node.getChildren() != null && !node.getChildren().isEmpty()) {
                        List<WBSSetup> filteredChildren = new java.util.ArrayList<>();
                        for (WBSSetup child : node.getChildren()) {
                                WBSSetup filteredChild = filterWBSTree(child, wbsType, activityTypeId, activityStatus,
                                                deliverable,
                                                billable);
                                if (filteredChild != null) {
                                        filteredChildren.add(filteredChild);
                                }
                        }
                        // If any children match, include the parent node with filtered children
                        if (!filteredChildren.isEmpty()) {
                                WBSSetup filteredNode = new WBSSetup();
                                // Copy all properties from original node
                                copyWBSProperties(node, filteredNode);
                                filteredNode.setChildren(filteredChildren);
                                return filteredNode;
                        }
                        return null;
                }

                // If node matches, filter its children and return
                if (matches) {
                        WBSSetup filteredNode = new WBSSetup();
                        copyWBSProperties(node, filteredNode);

                        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                                List<WBSSetup> filteredChildren = new java.util.ArrayList<>();
                                for (WBSSetup child : node.getChildren()) {
                                        WBSSetup filteredChild = filterWBSTree(child, wbsType, activityTypeId,
                                                        activityStatus, deliverable,
                                                        billable);
                                        if (filteredChild != null) {
                                                filteredChildren.add(filteredChild);
                                        }
                                }
                                filteredNode.setChildren(filteredChildren);
                        }
                        return filteredNode;
                }

                return null;
        }

        private void buildChildrenByProject(WBSSetup parent, Long projectId) {
                List<WBSSetup> children = wbsSetupRepository.findByProjectIdAndParentId(projectId, parent.getId());
                if (children != null && !children.isEmpty()) {
                        parent.setChildren(children);
                        // Calculate aggregated values for Components
                        if ("COMPONENT".equals(parent.getWbsType())
                                        || (parent.getLevel() != null && parent.getLevel() == 0)) {
                                calculateComponentAggregates(parent, children);
                        }
                        // Calculate aggregated values for Activities that have Sub Activities
                        else if (("ACTIVITY".equals(parent.getWbsType())
                                        || (parent.getLevel() != null && parent.getLevel() == 1))
                                        && hasSubActivities(children)) {
                                calculateActivityAggregates(parent, children);
                        }
                        for (WBSSetup child : children) {
                                buildChildrenByProject(child, projectId);
                        }
                }
        }

        public WBSSetup updateWbsComponent(Long id, String description, String wbsCode, String wbsType, Date startDate,
                        Date dueDate, String status, Date actualStartDate, Date actualEndDate, String assignedTo) {
                Optional<WBSSetup> opt = wbsSetupRepository.findById(id);
                if (opt.isPresent()) {
                        WBSSetup setup = opt.get();
                        if (description != null)
                                setup.setDescription(description);
                        if (wbsCode != null)
                                setup.setWbsCode(wbsCode);
                        if (wbsType != null)
                                setup.setWbsType(wbsType);
                        if (startDate != null)
                                setup.setStartDate(startDate);
                        if (dueDate != null)
                                setup.setDueDate(dueDate);
                        if (status != null)
                                setup.setStatus(status);
                        // Ignore actualStartDate/actualEndDate/assignedTo if they're not in DB or map
                        // them properly if they exist.
                        // Since they are not explicitly mapped on WBSSetup we update what we have.

                        return wbsSetupRepository.save(setup);
                }
                throw new RuntimeException("Component not found with ID: " + id);
        }

        /**
         * Check if an Activity has Sub Activities
         */
        private boolean hasSubActivities(List<WBSSetup> children) {
                if (children == null || children.isEmpty()) {
                        return false;
                }
                for (WBSSetup child : children) {
                        if ("SUB_ACTIVITY".equals(child.getWbsType())
                                        || (child.getLevel() != null && child.getLevel() == 2)) {
                                return true;
                        }
                }
                return false;
        }

        /**
         * Copy WBS properties from source to target
         */
        private void copyWBSProperties(WBSSetup source, WBSSetup target) {
                target.setId(source.getId());
                target.setWbsCode(source.getWbsCode());
                target.setDescription(source.getDescription());
                target.setProject(source.getProject());
                target.setParentId(source.getParentId());
                target.setLevel(source.getLevel());
                target.setWbsType(source.getWbsType());
                target.setComponentType(source.getComponentType());
                target.setActivityType(source.getActivityType());
                target.setStartDate(source.getStartDate());
                target.setDueDate(source.getDueDate());
                target.setProgress(source.getProgress());
                target.setStatus(source.getStatus());
                target.setPriority(source.getPriority());
                target.setEstimatedHours(source.getEstimatedHours());
                target.setActualHours(source.getActualHours());
                target.setSortOrder(source.getSortOrder());
                target.setActiveStatus(source.getActiveStatus());
                // target.setCreatedBy(source.getCreatedBy());
                // target.setUpdatedBy(source.getUpdatedBy());
                target.setCreatedOn(source.getCreatedOn());
                target.setUpdatedOn(source.getUpdatedOn());
                target.setFileName(source.getFileName());
                target.setFilePath(source.getFilePath());
                target.setIsDeliverable(source.getIsDeliverable());
                target.setClientApprovalNeeded(source.getClientApprovalNeeded());
                target.setIsBillable(source.getIsBillable());
                target.setBillableAmount(source.getBillableAmount());
                target.setBillableAmountIncTax(source.getBillableAmountIncTax());
                target.setTaxRate(source.getTaxRate());
                target.setDeliverableType(source.getDeliverableType());
                target.setDeliverableFrequency(source.getDeliverableFrequency());
                // target.setAssignedTo(source.getAssignedTo());
        }

        private void calculateComponentAggregates(WBSSetup component, List<WBSSetup> children) {
                double totalEstimatedHours = 0.0;
                double totalActualHours = 0.0;
                int totalProgress = 0;
                int childCount = 0;

                for (WBSSetup child : children) {
                        // Aggregate from Activities (level 1)
                        if ("ACTIVITY".equals(child.getWbsType())
                                        || (child.getLevel() != null && child.getLevel() == 1)) {
                                if (child.getEstimatedHours() != null) {
                                        totalEstimatedHours += child.getEstimatedHours();
                                }
                                if (child.getActualHours() != null) {
                                        totalActualHours += child.getActualHours();
                                }
                                if (child.getProgress() != null) {
                                        totalProgress += child.getProgress();
                                }
                                childCount++;
                        }
                        // Aggregate from nested Components (level 0) - use their aggregated values
                        else if ("COMPONENT".equals(child.getWbsType())
                                        || (child.getLevel() != null && child.getLevel() == 0)) {
                                // Use the child component's aggregated values (which may include its own
                                // children)
                                if (child.getEstimatedHours() != null) {
                                        totalEstimatedHours += child.getEstimatedHours();
                                }
                                if (child.getActualHours() != null) {
                                        totalActualHours += child.getActualHours();
                                }
                                if (child.getProgress() != null) {
                                        totalProgress += child.getProgress();
                                }
                                childCount++;
                        }
                }

                // Set calculated values (using Transient fields or we can add them to the
                // entity)
                component.setEstimatedHours(totalEstimatedHours);
                component.setActualHours(totalActualHours);
                if (childCount > 0) {
                        component.setProgress(totalProgress / childCount);
                } else {
                        component.setProgress(0);
                }
        }

        /**
         * Calculate aggregated values for Activities that have Sub Activities
         * Similar to calculateComponentAggregates but for Activities summing Sub
         * Activities
         */
        private void calculateActivityAggregates(WBSSetup activity, List<WBSSetup> subActivities) {
                double totalEstimatedHours = 0.0;
                double totalActualHours = 0.0;
                int totalProgress = 0;
                int subActivityCount = 0;

                for (WBSSetup subActivity : subActivities) {
                        if ("SUB_ACTIVITY".equals(subActivity.getWbsType())
                                        || (subActivity.getLevel() != null && subActivity.getLevel() == 2)) {
                                if (subActivity.getEstimatedHours() != null) {
                                        totalEstimatedHours += subActivity.getEstimatedHours();
                                }
                                if (subActivity.getActualHours() != null) {
                                        totalActualHours += subActivity.getActualHours();
                                }
                                if (subActivity.getProgress() != null) {
                                        totalProgress += subActivity.getProgress();
                                }
                                subActivityCount++;
                        }
                }

                // Set calculated values - sum of Sub Activities
                activity.setEstimatedHours(totalEstimatedHours);
                activity.setActualHours(totalActualHours);
                if (subActivityCount > 0) {
                        activity.setProgress(totalProgress / subActivityCount);
                } else {
                        activity.setProgress(0);
                }
        }

        public ByteArrayOutputStream generateWBSImportTemplate() throws IOException {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("WBS Import Template");

                // Create header style
                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 12);
                headerStyle.setFont(headerFont);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                headerStyle.setFillForegroundColor(
                                org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);

                // Create instruction style
                CellStyle instructionStyle = workbook.createCellStyle();
                Font instructionFont = workbook.createFont();
                instructionFont.setItalic(true);
                instructionFont.setColor(org.apache.poi.ss.usermodel.IndexedColors.BLUE.getIndex());
                instructionStyle.setFont(instructionFont);
                instructionStyle.setWrapText(true);

                // Create example row style
                CellStyle exampleStyle = workbook.createCellStyle();
                exampleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                exampleStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_YELLOW.getIndex());
                exampleStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
                exampleStyle.setBorderBottom(BorderStyle.THIN);
                exampleStyle.setBorderTop(BorderStyle.THIN);
                exampleStyle.setBorderLeft(BorderStyle.THIN);
                exampleStyle.setBorderRight(BorderStyle.THIN);

                int rowNum = 0;

                // Instructions row
                Row instructionRow = sheet.createRow(rowNum++);
                Cell instructionCell = instructionRow.createCell(0);
                instructionCell.setCellValue(
                                "Instructions: Fill in the data below. Required fields: WBS Code, Description, WBS Type. Level and Status are auto-calculated. Parent Code is optional for hierarchy.");
                instructionCell.setCellStyle(instructionStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));

                // Empty row
                rowNum++;

                // Header row
                Row headerRow = sheet.createRow(rowNum++);
                String[] headers = { "WBS Code", "Description", "WBS Type", "Parent Code", "Component Type",
                                "Activity Type", "Start Date (dd-MM-yyyy)", "Due Date (dd-MM-yyyy)", "Priority",
                                "Estimated Hours", "Assigned To (User Name)", "Is Deliverable (Yes/No)",
                                "Client Approval Needed (Yes/No)",
                                "Deliverable Frequency" };

                for (int i = 0; i < headers.length; i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(headers[i]);
                        cell.setCellStyle(headerStyle);
                }

                // Example row 1 - Component
                Row exampleRow1 = sheet.createRow(rowNum++);
                exampleRow1.createCell(0).setCellValue("C001");
                exampleRow1.createCell(1).setCellValue("Component Example");
                exampleRow1.createCell(2).setCellValue("COMPONENT");
                exampleRow1.createCell(3).setCellValue("");
                exampleRow1.createCell(4).setCellValue("Component Type Name");
                exampleRow1.createCell(5).setCellValue("");
                exampleRow1.createCell(6).setCellValue("01-01-2024");
                exampleRow1.createCell(7).setCellValue("31-12-2024");
                exampleRow1.createCell(8).setCellValue("Normal");
                exampleRow1.createCell(9).setCellValue(100.0);
                exampleRow1.createCell(10).setCellValue("");
                exampleRow1.createCell(11).setCellValue("No");
                exampleRow1.createCell(12).setCellValue("No");
                exampleRow1.createCell(13).setCellValue("");

                for (int i = 0; i < headers.length; i++) {
                        exampleRow1.getCell(i).setCellStyle(exampleStyle);
                }

                // Example row 2 - Activity
                Row exampleRow2 = sheet.createRow(rowNum++);
                exampleRow2.createCell(0).setCellValue("C001.1");
                exampleRow2.createCell(1).setCellValue("Activity Example");
                exampleRow2.createCell(2).setCellValue("ACTIVITY");
                exampleRow2.createCell(3).setCellValue("C001");
                exampleRow2.createCell(4).setCellValue("");
                exampleRow2.createCell(5).setCellValue("Activity Type Name");
                exampleRow2.createCell(6).setCellValue("01-01-2024");
                exampleRow2.createCell(7).setCellValue("30-06-2024");
                exampleRow2.createCell(8).setCellValue("High");
                exampleRow2.createCell(9).setCellValue(50.0);
                exampleRow2.createCell(10).setCellValue("John Doe");
                exampleRow2.createCell(11).setCellValue("Yes");
                exampleRow2.createCell(12).setCellValue("Yes");
                exampleRow2.createCell(13).setCellValue("Weekly");

                for (int i = 0; i < headers.length; i++) {
                        exampleRow2.getCell(i).setCellStyle(exampleStyle);
                }

                // Example row 3 - Sub Activity
                Row exampleRow3 = sheet.createRow(rowNum++);
                exampleRow3.createCell(0).setCellValue("C001.1.1");
                exampleRow3.createCell(1).setCellValue("Sub Activity Example");
                exampleRow3.createCell(2).setCellValue("SUB_ACTIVITY");
                exampleRow3.createCell(3).setCellValue("C001.1");
                exampleRow3.createCell(4).setCellValue("");
                exampleRow3.createCell(5).setCellValue("Activity Type Name");
                exampleRow3.createCell(6).setCellValue("01-01-2024");
                exampleRow3.createCell(7).setCellValue("31-03-2024");
                exampleRow3.createCell(8).setCellValue("Normal");
                exampleRow3.createCell(9).setCellValue(25.0);
                exampleRow3.createCell(10).setCellValue("Jane Smith");
                exampleRow3.createCell(11).setCellValue("No");
                exampleRow3.createCell(12).setCellValue("No");
                exampleRow3.createCell(13).setCellValue("");

                for (int i = 0; i < headers.length; i++) {
                        exampleRow3.getCell(i).setCellStyle(exampleStyle);
                }

                // Create data validation helper using XSSF-specific helper for proper dropdown
                // support
                XSSFDataValidationHelper validationHelper = (XSSFDataValidationHelper) sheet.getDataValidationHelper();
                int firstDataRow = rowNum; // First row after examples (0-based, rowNum is already the next row)
                int lastDataRow = firstDataRow + 999; // Allow up to 1000 data rows (inclusive)

                // WBS Type dropdown (column 2, index 2)
                DataValidationConstraint wbsTypeConstraint = validationHelper.createExplicitListConstraint(
                                new String[] { "COMPONENT", "ACTIVITY", "SUB_ACTIVITY" });
                CellRangeAddressList wbsTypeRange = new CellRangeAddressList(firstDataRow, lastDataRow, 2, 2);
                XSSFDataValidation wbsTypeValidation = (XSSFDataValidation) validationHelper.createValidation(
                                wbsTypeConstraint,
                                wbsTypeRange);
                // Enable in-cell dropdown
                enableInCellDropdown(wbsTypeValidation);
                wbsTypeValidation.setShowErrorBox(true);
                wbsTypeValidation.setErrorStyle(org.apache.poi.ss.usermodel.DataValidation.ErrorStyle.STOP);
                wbsTypeValidation.createErrorBox("Invalid Value", "Please select COMPONENT, ACTIVITY, or SUB_ACTIVITY");
                wbsTypeValidation.setShowPromptBox(false);
                wbsTypeValidation.setEmptyCellAllowed(true);
                sheet.addValidationData(wbsTypeValidation);

                // Priority dropdown (column 8, index 8)
                DataValidationConstraint priorityConstraint = validationHelper.createExplicitListConstraint(
                                new String[] { "Low", "Normal", "High", "Critical" });
                CellRangeAddressList priorityRange = new CellRangeAddressList(firstDataRow, lastDataRow, 8, 8);
                XSSFDataValidation priorityValidation = (XSSFDataValidation) validationHelper
                                .createValidation(priorityConstraint, priorityRange);
                enableInCellDropdown(priorityValidation);
                priorityValidation.setShowErrorBox(true);
                priorityValidation.setErrorStyle(org.apache.poi.ss.usermodel.DataValidation.ErrorStyle.STOP);
                priorityValidation.createErrorBox("Invalid Value", "Please select Low, Normal, High, or Critical");
                priorityValidation.setShowPromptBox(false);
                priorityValidation.setEmptyCellAllowed(true);
                sheet.addValidationData(priorityValidation);

                // Is Deliverable dropdown (column 11, index 11)
                DataValidationConstraint isDeliverableConstraint = validationHelper.createExplicitListConstraint(
                                new String[] { "Yes", "No" });
                CellRangeAddressList isDeliverableRange = new CellRangeAddressList(firstDataRow, lastDataRow, 11, 11);
                XSSFDataValidation isDeliverableValidation = (XSSFDataValidation) validationHelper.createValidation(
                                isDeliverableConstraint,
                                isDeliverableRange);
                enableInCellDropdown(isDeliverableValidation);
                isDeliverableValidation.setShowErrorBox(true);
                isDeliverableValidation.setErrorStyle(org.apache.poi.ss.usermodel.DataValidation.ErrorStyle.STOP);
                isDeliverableValidation.createErrorBox("Invalid Value", "Please select Yes or No");
                isDeliverableValidation.setShowPromptBox(false);
                isDeliverableValidation.setEmptyCellAllowed(true);
                sheet.addValidationData(isDeliverableValidation);

                // Client Approval Needed dropdown (column 12, index 12)
                DataValidationConstraint clientApprovalConstraint = validationHelper.createExplicitListConstraint(
                                new String[] { "Yes", "No" });
                CellRangeAddressList clientApprovalRange = new CellRangeAddressList(firstDataRow, lastDataRow, 12, 12);
                XSSFDataValidation clientApprovalValidation = (XSSFDataValidation) validationHelper.createValidation(
                                clientApprovalConstraint,
                                clientApprovalRange);
                enableInCellDropdown(clientApprovalValidation);
                clientApprovalValidation.setShowErrorBox(true);
                clientApprovalValidation.setErrorStyle(org.apache.poi.ss.usermodel.DataValidation.ErrorStyle.STOP);
                clientApprovalValidation.createErrorBox("Invalid Value", "Please select Yes or No");
                clientApprovalValidation.setShowPromptBox(false);
                clientApprovalValidation.setEmptyCellAllowed(true);
                sheet.addValidationData(clientApprovalValidation);

                // Deliverable Frequency dropdown (column 13, index 13)
                List<SetupDeliverableFrequency> frequencies = setupDeliverableFrequencyRepository
                                .findAll();
                String[] frequencyNames = frequencies.stream()
                                .map(f -> f.getFrequencyType() != null ? f.getFrequencyType() : "")
                                .filter(name -> !name.isEmpty()).toArray(String[]::new);
                if (frequencyNames.length > 0) {
                        DataValidationConstraint frequencyConstraint = validationHelper
                                        .createExplicitListConstraint(frequencyNames);
                        CellRangeAddressList frequencyRange = new CellRangeAddressList(firstDataRow, lastDataRow, 13,
                                        13);
                        XSSFDataValidation frequencyValidation = (XSSFDataValidation) validationHelper
                                        .createValidation(frequencyConstraint, frequencyRange);
                        enableInCellDropdown(frequencyValidation);
                        frequencyValidation.setShowErrorBox(true);
                        frequencyValidation.setErrorStyle(org.apache.poi.ss.usermodel.DataValidation.ErrorStyle.STOP);
                        frequencyValidation.createErrorBox("Invalid Value",
                                        "Please select a valid Deliverable Frequency");
                        frequencyValidation.setShowPromptBox(false);
                        frequencyValidation.setEmptyCellAllowed(true);
                        sheet.addValidationData(frequencyValidation);
                }

                // Auto-size columns
                for (int i = 0; i < headers.length; i++) {
                        sheet.autoSizeColumn(i);
                        // Set minimum width
                        if (sheet.getColumnWidth(i) < 3000) {
                                sheet.setColumnWidth(i, 3000);
                        }
                }

                // Set specific widths for better readability
                sheet.setColumnWidth(1, 8000); // Description
                sheet.setColumnWidth(6, 5000); // Start Date
                sheet.setColumnWidth(7, 5000); // Due Date
                sheet.setColumnWidth(10, 6000); // Assigned To

                // Write to ByteArrayOutputStream
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                workbook.close();

                return outputStream;
        }

        /**
         * Helper method to enable in-cell dropdown for Excel data validation.
         * This method ensures that the showDropDown attribute is set to true in the
         * underlying XML structure.
         * 
         * @param validation The XSSFDataValidation object to configure
         */
        private void enableInCellDropdown(XSSFDataValidation validation) {
                // First, set suppressDropDownArrow to false to show the dropdown arrow
                validation.setSuppressDropDownArrow(false);

                // Then, explicitly set showDropDown to true in the underlying XML using
                // reflection
                // This is necessary because Excel requires both attributes to be set correctly
                try {
                        // Get the underlying CTDataValidation object
                        java.lang.reflect.Method getCTMethod = validation.getClass().getMethod("getCTDataValidation");
                        Object ctValidation = getCTMethod.invoke(validation);

                        // Set showDropDown to true
                        java.lang.reflect.Method setShowDropDownMethod = ctValidation.getClass()
                                        .getMethod("setShowDropDown", boolean.class);
                        setShowDropDownMethod.invoke(ctValidation, true);

                        // Also try to unset suppressDropDown if it exists
                        try {
                                java.lang.reflect.Method setSuppressDropDownMethod = ctValidation.getClass()
                                                .getMethod("setSuppressDropDown", boolean.class);
                                setSuppressDropDownMethod.invoke(ctValidation, false);
                        } catch (NoSuchMethodException e) {
                                // Method doesn't exist, that's okay
                        }
                } catch (Exception e) {
                        validation.setSuppressDropDownArrow(false);
                }
        }

        @Transactional
        public JsonObject importWBSFromExcel(MultipartFile file, Long projectId) throws IOException {
                JsonObject result = new JsonObject();
                JsonArray errors = new JsonArray();
                JsonArray successes = new JsonArray();

                if (file == null || file.isEmpty()) {
                        result.addProperty("error", "File is required");
                        return result;
                }

                // Get project
                ProjectDetails project = entityManager
                                .find(ProjectDetails.class, projectId);
                if (project == null) {
                        result.addProperty("error", "Project not found");
                        return result;
                }

                // // Get user
                // Optional<UserLogin> userOptional = userLoginRepository.findById(userId);
                // if (!userOptional.isPresent()) {
                // result.addProperty("error", "User not found");
                // return result;
                // }
                // UserLogin user = userOptional.get();

                // Parse Excel file
                InputStream inputStream = file.getInputStream();
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter formatter = new DataFormatter();

                // Skip header row (row 0) and instruction rows
                // Find the actual header row (should contain "WBS Code")
                int headerRowIndex = -1;
                for (int i = 0; i <= 5; i++) {
                        Row row = sheet.getRow(i);
                        if (row != null) {
                                Cell cell = row.getCell(0);
                                if (cell != null && "WBS Code"
                                                .equalsIgnoreCase(formatter.formatCellValue(cell).trim())) {
                                        headerRowIndex = i;
                                        break;
                                }
                        }
                }

                if (headerRowIndex == -1) {
                        result.addProperty("error", "Could not find header row in Excel file");
                        workbook.close();
                        return result;
                }

                // Map to store WBS items by code for parent lookup
                Map<String, WBSSetup> wbsMap = new HashMap<>();
                // Support multiple date formats:
                // 1. "dd-MM-yyyy" (01-12-2025) - day-month-year, 4-digit year
                // 2. "dd-MM-yy" (01-12-25) - day-month-year, 2-digit year
                // 3. "MM-d-yy" (12-1-25) - month-day-year, 2-digit year, single digit day
                // 4. "MM-dd-yy" (12-01-25) - month-day-year, 2-digit year, 2-digit day
                // 5. "M-d-yy" (1-1-25) - month-day-year, single digit month and day
                // 6. "dd-MMM-yyyy" (01-Dec-2025) - day-monthname-year, 3-letter month
                // abbreviation
                SimpleDateFormat dateFormatFull = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat dateFormatShort = new SimpleDateFormat("dd-MM-yy");
                SimpleDateFormat dateFormatMMddyy1 = new SimpleDateFormat("MM-d-yy"); // 12-1-25
                SimpleDateFormat dateFormatMMddyy2 = new SimpleDateFormat("MM-dd-yy"); // 12-01-25
                SimpleDateFormat dateFormatMddyy = new SimpleDateFormat("M-d-yy"); // 1-1-25
                SimpleDateFormat dateFormatDDMMMYYYY = new SimpleDateFormat("dd-MMM-yyyy"); // 01-Dec-2025
                // Set lenient to false for strict parsing
                dateFormatFull.setLenient(false);
                dateFormatShort.setLenient(false);
                dateFormatMMddyy1.setLenient(false);
                dateFormatMMddyy2.setLenient(false);
                dateFormatMddyy.setLenient(false);
                dateFormatDDMMMYYYY.setLenient(false);

                int successCount = 0;
                int errorCount = 0;

                // Process data rows (start from headerRowIndex + 1)
                for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                        Row row = sheet.getRow(i);
                        if (row == null) {
                                continue;
                        }

                        // Skip empty rows
                        Cell codeCell = row.getCell(0);
                        if (codeCell == null || formatter.formatCellValue(codeCell).trim().isEmpty()) {
                                continue;
                        }

                        try {
                                // Read data from Excel (new column order without Level and Status)
                                String wbsCode = formatter.formatCellValue(row.getCell(0)).trim();
                                String description = formatter.formatCellValue(row.getCell(1)).trim();
                                String wbsType = formatter.formatCellValue(row.getCell(2)).trim();
                                String parentCode = formatter.formatCellValue(row.getCell(3)).trim();
                                String componentTypeName = formatter.formatCellValue(row.getCell(4)).trim();
                                String activityTypeName = formatter.formatCellValue(row.getCell(5)).trim();
                                String startDateStr = row.getCell(6).toString();
                                String dueDateStr = row.getCell(7).toString();
                                String priority = formatter.formatCellValue(row.getCell(8)).trim();
                                String estimatedHoursStr = formatter.formatCellValue(row.getCell(9)).trim();
                                String assignedToName = formatter.formatCellValue(row.getCell(10)).trim();
                                String isDeliverable = formatter.formatCellValue(row.getCell(11)).trim();
                                String clientApprovalNeeded = formatter.formatCellValue(row.getCell(12)).trim();
                                String deliverableFrequencyName = formatter.formatCellValue(row.getCell(13)).trim();

                                // Validate required fields
                                if (wbsCode.isEmpty() || description.isEmpty() || wbsType.isEmpty()) {
                                        errors.add("Row " + (i + 1)
                                                        + ": Missing required fields (WBS Code, Description, WBS Type)");
                                        errorCount++;
                                        continue;
                                }

                                // Calculate level from WBS Type
                                Integer level;
                                String wbsTypeUpper = wbsType.toUpperCase();
                                if ("COMPONENT".equals(wbsTypeUpper)) {
                                        level = 0;
                                } else if ("ACTIVITY".equals(wbsTypeUpper)) {
                                        level = 1;
                                } else if ("SUB_ACTIVITY".equals(wbsTypeUpper)) {
                                        level = 2;
                                } else {
                                        errors.add("Row " + (i + 1) + ": Invalid WBS Type: " + wbsType
                                                        + ". Must be COMPONENT, ACTIVITY, or SUB_ACTIVITY");
                                        errorCount++;
                                        continue;
                                }

                                // Create WBS entity
                                WBSSetup wbs = new WBSSetup();
                                wbs.setProject(project);
                                wbs.setWbsCode(wbsCode);
                                wbs.setDescription(description);
                                wbs.setLevel(level);
                                wbs.setWbsType(wbsType.toUpperCase());

                                // Set parent if provided and validate parent-child relationship
                                if (!parentCode.isEmpty()) {
                                        WBSSetup parent = wbsMap.get(parentCode);
                                        if (parent == null) {
                                                // Try to find existing parent in database
                                                List<WBSSetup> existingParents = wbsSetupRepository
                                                                .findByProjectIdAndWbsCode(projectId, parentCode);
                                                if (!existingParents.isEmpty()) {
                                                        parent = existingParents.get(0);
                                                } else {
                                                        errors.add("Row " + (i + 1) + ": Parent code not found: "
                                                                        + parentCode);
                                                        errorCount++;
                                                        continue;
                                                }
                                        }

                                        // Validate parent-child relationship
                                        if (parent != null) {
                                                Integer parentLevel = parent.getLevel();
                                                String parentWbsType = parent.getWbsType();

                                                // COMPONENT can have parent COMPONENT (or no parent)
                                                if (level == 0) {
                                                        if (parentLevel != null && parentLevel == 0
                                                                        && "COMPONENT".equals(parentWbsType)) {
                                                                // Valid: Component under Component
                                                                wbs.setParentId(parent.getId());
                                                        } else {
                                                                errors.add("Row " + (i + 1)
                                                                                + ": Component can only have a Component as parent, but parent is: "
                                                                                + parentWbsType);
                                                                errorCount++;
                                                                continue;
                                                        }
                                                }
                                                // ACTIVITY must have parent COMPONENT
                                                else if (level == 1) {
                                                        if (parentLevel != null && parentLevel == 0
                                                                        && "COMPONENT".equals(parentWbsType)) {
                                                                // Valid: Activity under Component
                                                                wbs.setParentId(parent.getId());
                                                        } else {
                                                                errors.add(
                                                                                "Row " + (i + 1) + ": Activity must have a Component as parent, but parent is: "
                                                                                                + parentWbsType);
                                                                errorCount++;
                                                                continue;
                                                        }
                                                }
                                                // SUB_ACTIVITY must have parent ACTIVITY
                                                else if (level == 2) {
                                                        if (parentLevel != null && parentLevel == 1
                                                                        && "ACTIVITY".equals(parentWbsType)) {
                                                                // Valid: Sub Activity under Activity
                                                                wbs.setParentId(parent.getId());
                                                        } else {
                                                                errors.add("Row " + (i + 1)
                                                                                + ": Sub Activity must have an Activity as parent, but parent is: "
                                                                                + parentWbsType);
                                                                errorCount++;
                                                                continue;
                                                        }
                                                }
                                        }
                                } else {
                                        // No parent provided - validate that it's allowed
                                        // COMPONENT can have no parent (root level)
                                        // ACTIVITY must have a parent
                                        if (level == 1) {
                                                errors.add("Row " + (i + 1)
                                                                + ": Activity must have a parent Component");
                                                errorCount++;
                                                continue;
                                        }
                                        // SUB_ACTIVITY must have a parent
                                        if (level == 2) {
                                                errors.add("Row " + (i + 1)
                                                                + ": Sub Activity must have a parent Activity");
                                                errorCount++;
                                                continue;
                                        }
                                }

                                // Set Component Type
                                if (!componentTypeName.isEmpty() && level == 0) {
                                        List<ComponentType> componentTypes = componentTypeRepository
                                                        .findByTypeNameIgnoreCase(componentTypeName);
                                        if (!componentTypes.isEmpty()) {
                                                wbs.setComponentType(componentTypes.get(0));
                                        }
                                }

                                // Set Activity Type
                                if (!activityTypeName.isEmpty() && (level == 1 || level == 2)) {
                                        List<ActivityType> activityTypes = activityTypeRepository
                                                        .findByTypeNameIgnoreCase(activityTypeName);
                                        if (!activityTypes.isEmpty()) {
                                                wbs.setActivityType(activityTypes.get(0));
                                        }
                                }

                                // Parse dates - support multiple formats:
                                // 1. "dd-MM-yyyy" (01-12-2025) - day-month-year, 4-digit year
                                // 2. "dd-MM-yy" (01-12-25) - day-month-year, 2-digit year
                                // 3. "MM-d-yy" (12-1-25) - month-day-year, 2-digit year, single digit day
                                // 4. "MM-dd-yy" (12-01-25) - month-day-year, 2-digit year, 2-digit day
                                // 5. "M-d-yy" (1-1-25) - month-day-year, single digit month and day
                                // 6. "dd-MMM-yyyy" (01-Dec-2025) - day-monthname-year, 3-letter month
                                // abbreviation
                                Date parsedStartDate = null;
                                Date parsedDueDate = null;

                                if (!startDateStr.isEmpty()) {
                                        try {
                                                // Try formats in order of likelihood
                                                // 1. Try dd-MM-yyyy (01-12-2025)
                                                try {
                                                        parsedStartDate = dateFormatFull.parse(startDateStr);
                                                } catch (ParseException e1) {
                                                        // 2. Try dd-MM-yy (01-12-25)
                                                        try {
                                                                parsedStartDate = dateFormatShort.parse(startDateStr);
                                                        } catch (ParseException e2) {
                                                                // 3. Try dd-MMM-yyyy (01-Dec-2025) - day-monthname-year
                                                                try {
                                                                        parsedStartDate = dateFormatDDMMMYYYY
                                                                                        .parse(startDateStr);
                                                                } catch (ParseException e3) {
                                                                        // 4. Try MM-dd-yy (12-01-25) - month-day-year
                                                                        try {
                                                                                parsedStartDate = dateFormatMMddyy2
                                                                                                .parse(startDateStr);
                                                                        } catch (ParseException e4) {
                                                                                // 5. Try MM-d-yy (12-1-25) -
                                                                                // month-day-year, single digit day
                                                                                try {
                                                                                        parsedStartDate = dateFormatMMddyy1
                                                                                                        .parse(startDateStr);
                                                                                } catch (ParseException e5) {
                                                                                        // 6. Try M-d-yy (1-1-25) -
                                                                                        // month-day-year, single digit
                                                                                        // month and day
                                                                                        try {
                                                                                                parsedStartDate = dateFormatMddyy
                                                                                                                .parse(startDateStr);
                                                                                        } catch (ParseException e6) {
                                                                                                // All formats failed
                                                                                                errors.add("Row " + (i
                                                                                                                + 1)
                                                                                                                + ": Invalid start date format: "
                                                                                                                + startDateStr
                                                                                                                +
                                                                                                                " (expected dd-MM-yyyy, dd-MM-yy, dd-MMM-yyyy, MM-dd-yy, MM-d-yy, or M-d-yy)");
                                                                                                // logger.warn("Failed
                                                                                                // to parse start date
                                                                                                // for row "
                                                                                                // + (i + 1)
                                                                                                // + ": "
                                                                                                // + startDateStr);
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                                if (parsedStartDate != null) {
                                                        wbs.setStartDate(parsedStartDate);
                                                }
                                        } catch (Exception e) {
                                                // Already logged above, just continue
                                        }
                                }

                                if (!dueDateStr.isEmpty()) {
                                        try {
                                                // Try formats in order of likelihood
                                                // 1. Try dd-MM-yyyy (01-12-2025)
                                                try {
                                                        parsedDueDate = dateFormatFull.parse(dueDateStr);
                                                } catch (ParseException e1) {
                                                        // 2. Try dd-MM-yy (01-12-25)
                                                        try {
                                                                parsedDueDate = dateFormatShort.parse(dueDateStr);
                                                        } catch (ParseException e2) {
                                                                // 3. Try dd-MMM-yyyy (01-Dec-2025) - day-monthname-year
                                                                try {
                                                                        parsedDueDate = dateFormatDDMMMYYYY
                                                                                        .parse(dueDateStr);
                                                                } catch (ParseException e3) {
                                                                        // 4. Try MM-dd-yy (12-01-25) - month-day-year
                                                                        try {
                                                                                parsedDueDate = dateFormatMMddyy2
                                                                                                .parse(dueDateStr);
                                                                        } catch (ParseException e4) {
                                                                                // 5. Try MM-d-yy (12-1-25) -
                                                                                // month-day-year, single digit day
                                                                                try {
                                                                                        parsedDueDate = dateFormatMMddyy1
                                                                                                        .parse(dueDateStr);
                                                                                } catch (ParseException e5) {
                                                                                        // 6. Try M-d-yy (1-1-25) -
                                                                                        // month-day-year, single digit
                                                                                        // month and day
                                                                                        try {
                                                                                                parsedDueDate = dateFormatMddyy
                                                                                                                .parse(dueDateStr);
                                                                                        } catch (ParseException e6) {
                                                                                                // All formats failed
                                                                                                errors.add("Row " + (i
                                                                                                                + 1)
                                                                                                                + ": Invalid due date format: "
                                                                                                                + dueDateStr
                                                                                                                +
                                                                                                                " (expected dd-MM-yyyy, dd-MM-yy, dd-MMM-yyyy, MM-dd-yy, MM-d-yy, or M-d-yy)");
                                                                                                // logger.warn(
                                                                                                // "Failed to parse due
                                                                                                // date for row "
                                                                                                // + (i + 1)
                                                                                                // + ": "
                                                                                                // + dueDateStr);
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                                if (parsedDueDate != null) {
                                                        wbs.setDueDate(parsedDueDate);
                                                }
                                        } catch (Exception e) {
                                                // Already logged above, just continue
                                        }
                                }

                                // Set status default to "Pending"
                                wbs.setStatus("Pending");

                                // Set priority
                                if (!priority.isEmpty()) {
                                        wbs.setPriority(priority);
                                }
                                if (!estimatedHoursStr.isEmpty()) {
                                        try {
                                                wbs.setEstimatedHours(Double.parseDouble(estimatedHoursStr));
                                        } catch (NumberFormatException e) {
                                                // Ignore invalid number
                                        }
                                }
                                // Set Is Deliverable (Yes/No)
                                if (!isDeliverable.isEmpty()) {
                                        wbs.setIsDeliverable(isDeliverable.equalsIgnoreCase("Yes") ? "Y" : "N");
                                } else {
                                        wbs.setIsDeliverable("N");
                                }

                                // Set Client Approval Needed (Yes/No)
                                if (!clientApprovalNeeded.isEmpty()) {
                                        wbs.setClientApprovalNeeded(
                                                        clientApprovalNeeded.equalsIgnoreCase("Yes") ? "Y" : "N");
                                } else {
                                        wbs.setClientApprovalNeeded("N");
                                }

                                // Set Deliverable Frequency
                                // Check frequency column regardless of IsDeliverable - if frequency has value,
                                // use it
                                SetupDeliverableFrequency deliverableFrequency = null;
                                if (!deliverableFrequencyName.isEmpty()) {
                                        List<SetupDeliverableFrequency> allFrequencies = setupDeliverableFrequencyRepository
                                                        .findAll();
                                        Optional<SetupDeliverableFrequency> frequencyOptional = allFrequencies
                                                        .stream()
                                                        .filter(f -> f.getFrequencyType() != null
                                                                        && f.getFrequencyType().equalsIgnoreCase(
                                                                                        deliverableFrequencyName))
                                                        .findFirst();
                                        if (frequencyOptional.isPresent()) {
                                                deliverableFrequency = frequencyOptional.get();
                                                // Set frequency on WBS to enable subactivity creation based on
                                                // frequency
                                                // This allows creating subactivities even if IsDeliverable is "N"
                                                wbs.setDeliverableFrequency(deliverableFrequency);
                                        }
                                }
                                // Sort order will be auto-assigned based on order in Excel

                                // Set Assigned To
                                // if (!assignedToName.isEmpty()) {
                                // List<UserLogin> users = userLoginRepository
                                // .findByDisplayNameIgnoreCase(assignedToName);
                                // if (users.isEmpty()) {
                                // users = userLoginRepository.findByUserNameIgnoreCase(assignedToName);
                                // }
                                // if (!users.isEmpty()) {
                                // wbs.setAssignedTo(users.get(0));
                                // }
                                // }

                                // Set audit fields
                                // wbs.setCreatedBy(user);
                                // wbs.setUpdatedBy(user);
                                wbs.setCreatedOn(new Date());
                                wbs.setUpdatedOn(new Date());
                                wbs.setActiveStatus("Y");

                                // Save WBS
                                WBSSetup savedWBS = wbsSetupRepository.save(wbs);
                                wbsMap.put(wbsCode, savedWBS);
                                successes.add("Row " + (i + 1) + ": " + wbsCode + " - " + description);
                                successCount++;

                                // CRITICAL: If frequency column has value and start date exists, create
                                // subactivities
                                // Only create subactivities if:
                                // 1. Frequency is set (deliverableFrequency is not null)
                                // 2. Start date exists (parsedStartDate is not null) - REQUIRED per user
                                // requirement
                                // 3. Due date exists (parsedDueDate is not null) - needed to calculate number
                                // of occurrences
                                // 4. WBS is an Activity (level 1) or Sub Activity (level 2) - can have children
                                // Use parsed dates to ensure we check the actual parsed values, not database
                                // values
                                if (deliverableFrequency != null && parsedStartDate != null && parsedDueDate != null) {
                                        Integer wbsLevel = savedWBS.getLevel();
                                        // Only create subactivities for Activity (level 1) or Sub Activity (level 2)
                                        if (wbsLevel != null && (wbsLevel == 1 || wbsLevel == 2)) {
                                                try {
                                                        logger.info("Creating subactivities for WBS " + wbsCode
                                                                        + " (Row " + (i + 1) +
                                                                        ") - Frequency: "
                                                                        + deliverableFrequency.getFrequencyType() +
                                                                        ", Start Date: " + savedWBS.getStartDate() +
                                                                        ", Due Date: " + savedWBS.getDueDate());

                                                        // Refresh savedWBS to ensure all fields are loaded
                                                        savedWBS = wbsSetupRepository.findById(savedWBS.getId())
                                                                        .orElse(savedWBS);

                                                        // CRITICAL: Ensure deliverableFrequency and dates are set on
                                                        // refreshed entity
                                                        // (they might not be loaded due to lazy loading or transaction
                                                        // issues)
                                                        boolean needsUpdate = false;
                                                        if (savedWBS.getDeliverableFrequency() == null) {
                                                                savedWBS.setDeliverableFrequency(deliverableFrequency);
                                                                needsUpdate = true;
                                                                logger.info("Set deliverableFrequency on refreshed WBS "
                                                                                + wbsCode);
                                                        }
                                                        if (savedWBS.getStartDate() == null
                                                                        && parsedStartDate != null) {
                                                                savedWBS.setStartDate(parsedStartDate);
                                                                needsUpdate = true;
                                                                logger.info("Set startDate on refreshed WBS "
                                                                                + wbsCode);
                                                        }
                                                        if (savedWBS.getDueDate() == null && parsedDueDate != null) {
                                                                savedWBS.setDueDate(parsedDueDate);
                                                                needsUpdate = true;
                                                                logger.info("Set dueDate on refreshed WBS " + wbsCode);
                                                        }

                                                        // Save if any fields were updated
                                                        if (needsUpdate) {
                                                                savedWBS = wbsSetupRepository.save(savedWBS);
                                                        }

                                                        // Final verification - ensure all required fields are set
                                                        if (savedWBS.getDeliverableFrequency() == null) {
                                                                logger.error("DeliverableFrequency is still null after refresh for WBS "
                                                                                + wbsCode);
                                                                errors.add("Row " + (i + 1)
                                                                                + ": Failed to set frequency for "
                                                                                + wbsCode);
                                                        } else if (savedWBS.getStartDate() == null
                                                                        || savedWBS.getDueDate() == null) {
                                                                logger.warn("Dates are null after refresh for WBS "
                                                                                + wbsCode +
                                                                                " - Start: " + savedWBS.getStartDate() +
                                                                                ", Due: " + savedWBS.getDueDate());
                                                                errors.add("Row " + (i + 1)
                                                                                + ": Dates lost after refresh for "
                                                                                + wbsCode);
                                                        } else {
                                                                // All fields are set - create child subactivities based
                                                                // on frequency
                                                                logger.info("Calling createDeliverableChildActivities for WBS "
                                                                                + wbsCode +
                                                                                " - Frequency: "
                                                                                + savedWBS.getDeliverableFrequency()
                                                                                                .getFrequencyType()
                                                                                +
                                                                                ", Start: " + savedWBS.getStartDate() +
                                                                                ", Due: " + savedWBS.getDueDate());
                                                                createDeliverableChildActivities(savedWBS);
                                                                successes.add("Row " + (i + 1)
                                                                                + ": Created subactivities for "
                                                                                + wbsCode
                                                                                + " based on frequency");
                                                        }
                                                } catch (Exception e) {
                                                        logger.error("Error creating subactivities for WBS " + wbsCode
                                                                        + " (Row " + (i + 1) + ")",
                                                                        e);
                                                        errors.add("Row " + (i + 1)
                                                                        + ": Failed to create subactivities for "
                                                                        + wbsCode + ": "
                                                                        + e.getMessage());
                                                }
                                        } else {
                                                logger.debug("Skipping subactivity creation for WBS " + wbsCode +
                                                                " - Level is " + wbsLevel + " (must be 1 or 2)");
                                        }
                                } else {
                                        // Log why subactivities are not being created
                                        if (deliverableFrequency == null) {
                                                logger.debug("Skipping subactivity creation for WBS " + wbsCode
                                                                + " - Frequency is null");
                                        } else if (parsedStartDate == null) {
                                                // Frequency is set but start date is missing - log warning
                                                errors.add("Row " + (i + 1) + ": Frequency specified for " + wbsCode
                                                                + " but start date is missing. Subactivities not created.");
                                                logger.warn("Frequency specified for " + wbsCode
                                                                + " but start date is missing");
                                        } else if (parsedDueDate == null) {
                                                // Frequency is set but due date is missing - log warning
                                                errors.add("Row " + (i + 1) + ": Frequency specified for " + wbsCode
                                                                + " but due date is missing. Subactivities not created.");
                                                logger.warn("Frequency specified for " + wbsCode
                                                                + " but due date is missing");
                                        }
                                }

                        } catch (Exception e) {
                                logger.error("Error importing row " + (i + 1), e);
                                errors.add("Row " + (i + 1) + ": " + e.getMessage());
                                errorCount++;
                        }
                }

                workbook.close();

                result.addProperty("success", errorCount == 0);
                result.addProperty("successCount", successCount);
                result.addProperty("errorCount", errorCount);
                result.add("successes", successes);
                result.add("errors", errors);

                return result;
        }

        /**
         * Create child activities/sub-activities based on deliverable frequency
         */
        private void createDeliverableChildActivities(WBSSetup parentWBS) {
                try {
                        if (parentWBS.getDeliverableFrequency() == null) {
                                logger.warn("Cannot create child activities: deliverableFrequency is null for WBS ID: "
                                                + parentWBS.getId());
                                return;
                        }

                        String frequencyType = parentWBS.getDeliverableFrequency().getFrequencyType();
                        if (frequencyType == null || frequencyType.trim().isEmpty()) {
                                logger.warn("Cannot create child activities: frequencyType is null or empty for WBS ID: "
                                                + parentWBS.getId());
                                return;
                        }

                        Date startDate = parentWBS.getStartDate();
                        Date endDate = parentWBS.getDueDate();

                        if (startDate == null || endDate == null) {
                                logger.warn("Cannot create child activities - dates are null for WBS ID: "
                                                + parentWBS.getId());
                                return;
                        }

                        if (endDate.before(startDate)) {
                                logger.warn("Cannot create child activities - end date is before start date for WBS ID: "
                                                + parentWBS.getId());
                                return;
                        }

                        // Calculate number of occurrences based on frequency
                        int numberOfOccurrences = getNumberOfOccurrences(frequencyType, startDate, endDate);

                        // Determine child level:
                        // - If parent is Activity (level 1), create Sub Activities (level 2)
                        // - If parent is Sub Activity (level 2), create Sub Activities (level 2)
                        Integer parentLevelObj = parentWBS.getLevel();
                        int parentLevel = parentLevelObj != null ? parentLevelObj.intValue() : 0;

                        int childLevel;
                        String childWbsType;

                        // Create Sub Activities for both Activity and Sub Activity parents
                        if (parentLevel == 1) {
                                // Parent is Activity (level 1), create Sub Activities (level 2)
                                childLevel = 2;
                                childWbsType = "SUB_ACTIVITY";
                        } else if (parentLevel == 2) {
                                // Parent is Sub Activity (level 2), create Sub Activities (level 2)
                                childLevel = 2;
                                childWbsType = "SUB_ACTIVITY";
                        } else {
                                // Parent is Component (level 0) or unknown level - shouldn't happen for
                                // deliverables
                                // But if it does, default to Sub Activity level 2
                                childLevel = 2;
                                childWbsType = "SUB_ACTIVITY";
                        }
                        // Get existing children to avoid duplicates
                        List<WBSSetup> existingChildren = wbsSetupRepository.findAllByProjectIdAndParentId(
                                        parentWBS.getProject().getProjectId(), parentWBS.getId());

                        // Create child activities for each occurrence
                        int createdCount = 0;

                        for (int i = 0; i < numberOfOccurrences; i++) {
                                Date deliveryDate = calculateNextDeliveryDate(startDate, frequencyType, i);

                                // Check if child already exists for this date
                                boolean childExists = false;
                                for (WBSSetup existing : existingChildren) {
                                        if (existing.getStartDate() != null &&
                                                        existing.getStartDate().equals(deliveryDate)) {
                                                childExists = true;
                                                break;
                                        }
                                }

                                if (!childExists) {
                                        WBSSetup childWBS = new WBSSetup();
                                        childWBS.setProject(parentWBS.getProject());
                                        childWBS.setParentId(parentWBS.getId());

                                        // CRITICAL: Set level and type FIRST, before any other fields
                                        // This ensures children are at the same level as parent
                                        childWBS.setLevel(childLevel);
                                        childWBS.setWbsType(childWbsType);

                                        // Verify level is set correctly (defensive check)
                                        if (childWBS.getLevel() == null || !childWBS.getLevel().equals(childLevel)) {
                                                logger.warn("Child level mismatch! Expected: " + childLevel + ", Got: "
                                                                + childWBS.getLevel());
                                                childWBS.setLevel(childLevel);
                                        }

                                        childWBS.setDescription(parentWBS.getDescription() + " - " + (i + 1));
                                        childWBS.setWbsCode(parentWBS.getWbsCode() + "." + (i + 1));
                                        childWBS.setStartDate(deliveryDate);
                                        childWBS.setDueDate(deliveryDate);
                                        childWBS.setIsDeliverable("N");
                                        childWBS.setClientApprovalNeeded(parentWBS.getClientApprovalNeeded());
                                        // Load activityType from database if parent has it
                                        if (parentWBS.getActivityType() != null
                                                        && parentWBS.getActivityType().getId() != null) {
                                                Optional<ActivityType> activityTypeOptional = activityTypeRepository
                                                                .findById(parentWBS.getActivityType().getId());
                                                if (activityTypeOptional.isPresent()) {
                                                        childWBS.setActivityType(activityTypeOptional.get());
                                                }
                                        }
                                        childWBS.setStatus("Not Started");
                                        childWBS.setSortOrder(i + 1);
                                        childWBS.setActiveStatus("Y");
                                        // childWBS.setCreatedBy(user);
                                        childWBS.setCreatedOn(new Date());
                                        // childWBS.setUpdatedBy(user);
                                        childWBS.setUpdatedOn(new Date());

                                        // Save directly to repository - bypass saveWBS method to avoid level
                                        // recalculation
                                        WBSSetup savedChild = wbsSetupRepository.save(childWBS);

                                        // Final verification - log if level is incorrect
                                        if (savedChild.getLevel() == null
                                                        || !savedChild.getLevel().equals(childLevel)) {
                                                logger.error("Child saved with incorrect level! Expected: " + childLevel
                                                                +
                                                                ", Got: " + savedChild.getLevel() + ", Child ID: "
                                                                + savedChild.getId());
                                        }

                                        createdCount++;
                                }
                        }

                        if (createdCount > 0) {
                                logger.info("Created " + createdCount + " child activities for WBS ID: "
                                                + parentWBS.getId());
                        }
                } catch (Exception e) {
                        logger.error("Error creating deliverable child activities", e);
                }
        }

        /**
         * Calculate number of occurrences based on frequency type
         */
        private int getNumberOfOccurrences(String frequencyType, Date startDate, Date endDate) {
                if (frequencyType == null || startDate == null || endDate == null) {
                        return 1;
                }

                if (endDate.before(startDate)) {
                        return 1;
                }

                java.util.Calendar startCal = java.util.Calendar.getInstance();
                startCal.setTime(startDate);
                java.util.Calendar endCal = java.util.Calendar.getInstance();
                endCal.setTime(endDate);

                String freq = frequencyType.toLowerCase().trim();
                int count = 0;
                java.util.Calendar currentCal = (java.util.Calendar) startCal.clone();

                if (freq.contains("weekly") || freq.contains("week")) {
                        while (!currentCal.after(endCal)) {
                                count++;
                                currentCal.add(java.util.Calendar.WEEK_OF_YEAR, 1);
                        }
                } else if (freq.contains("monthly") || freq.contains("month")) {
                        while (!currentCal.after(endCal)) {
                                count++;
                                currentCal.add(java.util.Calendar.MONTH, 1);
                        }
                } else if (freq.contains("quarterly") || freq.contains("quarter")) {
                        while (!currentCal.after(endCal)) {
                                count++;
                                currentCal.add(java.util.Calendar.MONTH, 3);
                        }
                } else if (freq.contains("half-yearly") || freq.contains("half yearly")
                                || freq.contains("semi-annual")) {
                        while (!currentCal.after(endCal)) {
                                count++;
                                currentCal.add(java.util.Calendar.MONTH, 6);
                        }
                } else if (freq.contains("yearly") || freq.contains("annual") || freq.contains("year")) {
                        while (!currentCal.after(endCal)) {
                                count++;
                                currentCal.add(java.util.Calendar.YEAR, 1);
                        }
                } else {
                        return 1;
                }

                return count > 0 ? count : 1;
        }

        /**
         * Calculate next delivery date based on frequency and occurrence number
         * occurrenceNumber: 0 = first occurrence (startDate), 1 = second occurrence,
         * etc.
         */
        private Date calculateNextDeliveryDate(Date startDate, String frequencyType, int occurrenceNumber) {
                if (startDate == null) {
                        return new Date();
                }

                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.setTime(startDate);

                String freq = frequencyType != null ? frequencyType.toLowerCase().trim() : "";

                if (freq.contains("weekly") || freq.contains("week")) {
                        calendar.add(java.util.Calendar.WEEK_OF_YEAR, occurrenceNumber);
                } else if (freq.contains("monthly") || freq.contains("month")) {
                        calendar.add(java.util.Calendar.MONTH, occurrenceNumber);
                } else if (freq.contains("quarterly") || freq.contains("quarter")) {
                        calendar.add(java.util.Calendar.MONTH, occurrenceNumber * 3);
                } else if (freq.contains("half-yearly") || freq.contains("half yearly")
                                || freq.contains("semi-annual")) {
                        calendar.add(java.util.Calendar.MONTH, occurrenceNumber * 6);
                } else if (freq.contains("yearly") || freq.contains("annual") || freq.contains("year")) {
                        calendar.add(java.util.Calendar.YEAR, occurrenceNumber);
                }

                return calendar.getTime();
        }

        @Transactional
        public void updateHierarchy(Long draggedItemId, Long targetId, String dropPosition) {
                if (draggedItemId == null || targetId == null || dropPosition == null)
                        return;

                Optional<WBSSetup> draggedOpt = wbsSetupRepository.findById(draggedItemId);
                Optional<WBSSetup> targetOpt = wbsSetupRepository.findById(targetId);

                if (!draggedOpt.isPresent() || !targetOpt.isPresent())
                        return;

                WBSSetup dragged = draggedOpt.get();
                WBSSetup target = targetOpt.get();
                Long projectId = target.getProject().getProjectId();

                if ("inside".equalsIgnoreCase(dropPosition)) {
                        dragged.setParentId(target.getId());
                        dragged.setLevel((target.getLevel() != null ? target.getLevel() : 0) + 1);

                        // put at the end of children
                        List<WBSSetup> children = wbsSetupRepository.findByProjectIdAndParentId(projectId,
                                        target.getId());
                        int maxSort = 0;
                        for (WBSSetup c : children) {
                                if (c.getSortOrder() != null && c.getSortOrder() > maxSort) {
                                        maxSort = c.getSortOrder();
                                }
                        }
                        dragged.setSortOrder(maxSort + 1);
                } else {
                        // "before" or "after"
                        dragged.setParentId(target.getParentId());
                        dragged.setLevel(target.getLevel() != null ? target.getLevel() : 0);

                        // Push siblings down
                        List<WBSSetup> siblings;
                        if (target.getParentId() == null) {
                                siblings = wbsSetupRepository.findRootWBSByProjectId(projectId);
                        } else {
                                siblings = wbsSetupRepository.findByProjectIdAndParentId(projectId,
                                                target.getParentId());
                        }

                        int newSortOrder = (target.getSortOrder() != null ? target.getSortOrder() : 0);
                        if ("after".equalsIgnoreCase(dropPosition)) {
                                newSortOrder++;
                        }

                        for (WBSSetup sib : siblings) {
                                if (sib.getId().equals(dragged.getId()))
                                        continue;
                                int sibSort = (sib.getSortOrder() != null ? sib.getSortOrder() : 0);
                                if (sibSort >= newSortOrder) {
                                        sib.setSortOrder(sibSort + 1);
                                        wbsSetupRepository.save(sib);
                                }
                        }
                        dragged.setSortOrder(newSortOrder);
                }
                wbsSetupRepository.save(dragged);
        }

}