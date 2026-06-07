package com.cis.api.controller;

import com.cis.api.service.WbsService;
import com.cis.api.vo.WbsResponseVo;
import com.cis.api.entity.WBSSetup;
import com.cis.api.entity.WBSVersion;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * WBS (Work Breakdown Structure) read-only REST API.
 *
 * Base URL: /cis_api/api/wbs
 *
 * Endpoints:
 * GET /cis_api/api/wbs/{cmmiProjectId}
 * → returns WBS for all modules
 *
 * GET /cis_api/api/wbs/{cmmiProjectId}?moduleId=5
 * → returns WBS filtered to module 5
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/wbs")
public class WbsController {

    @Autowired
    private WbsService wbsService;

    /**
     * Get the WBS view for a given projectId.
     *
     * @param ProjectId the ID of the cmmi_project row
     * @param moduleId  optional module filter (0 or omit = all modules)
     *
     *                  GET http://localhost:8080/cis_api/api/wbs/239
     *                  GET http://localhost:8080/cis_api/api/wbs/239?moduleId=5
     */
    @GetMapping("galaxy-wbs/{projectId}")
    public ResponseEntity<WbsResponseVo> getWbs(
            @PathVariable("projectId") Integer projectId,
            @RequestParam(value = "moduleId", required = false, defaultValue = "0") Integer moduleId) {
        WbsResponseVo response = wbsService.getWbsView(projectId, moduleId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/downloadWBSExcel")
    public void downloadWBSExcel(@RequestParam(value = "projectId", required = false) String projectIdEncrypted,
            @RequestParam(value = "wbsType", required = false) String wbsType,
            @RequestParam(value = "activityTypeId", required = false) Long activityTypeId,
            @RequestParam(value = "activityStatus", required = false) String activityStatus,
            @RequestParam(value = "deliverable", required = false) String deliverable,
            @RequestParam(value = "billable", required = false) String billable,
            @RequestParam(value = "projectId", required = false) Long projectId,
            HttpServletResponse response) throws IOException {
        try {
            if (projectIdEncrypted == null || projectIdEncrypted.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Project ID is required");
                return;
            }
            // Long projectId = new Utility().decrypt(projectIdEncrypted);

            if (projectId == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Project ID");
                return;
            }
            ByteArrayOutputStream excelStream = wbsService.generateWBSExcelByProjectId(projectId, wbsType,
                    activityTypeId, activityStatus, deliverable, billable);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"WBS_Report.xlsx\"");
            response.setContentLength(excelStream.size());
            response.getOutputStream().write(excelStream.toByteArray());
            response.getOutputStream().flush();
        } catch (Exception e) {
            // logger.error("Error generating WBS Excel", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error generating Excel file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/importWBSModal")
    public ModelAndView importWBSModal(
            @RequestParam(value = "projectIdEncrypted", required = false) String projectIdEncrypted) {
        ModelAndView modelAndView = new ModelAndView("wbs/ImportWBSModal");
        return modelAndView;
    }

    @GetMapping(value = "/downloadWBSImportTemplate")
    public void downloadWBSImportTemplate(HttpServletResponse response) throws IOException {
        try {
            ByteArrayOutputStream templateStream = wbsService.generateWBSImportTemplate();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"WBS_Import_Template.xlsx\"");
            response.setContentLength(templateStream.size());
            response.getOutputStream().write(templateStream.toByteArray());
            response.getOutputStream().flush();
        } catch (Exception e) {
            // logger.error("Error generating WBS Import Template", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error generating template file: " + e.getMessage());
        }
    }

    @PostMapping(value = "/importWBSFromExcel")
    @ResponseBody
    public void importWBSFromExcel(@RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "projectId", required = true) Long projectId,
            HttpSession session,
            HttpServletResponse response) throws IOException {
        JsonObject result = new JsonObject();

        try {
            if (projectId == null) {
                result.addProperty("error", "Project ID is required");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Project ID is required");
                return;
            }

            result = wbsService.importWBSFromExcel(file, projectId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write(result.toString());
        } catch (Exception e) {
            result.addProperty("error", "Error importing Excel file: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write(result.toString());
        }
    }

    @GetMapping("/components/list")
    public ResponseEntity<List<WBSSetup>> getWbsComponents(
            @RequestParam(value = "projectId", required = true) Long projectId,
            @RequestParam(value = "wbsType", required = false) String wbsType,
            @RequestParam(value = "activityTypeId", required = false) Long activityTypeId,
            @RequestParam(value = "activityStatus", required = false) String activityStatus,
            @RequestParam(value = "deliverable", required = false) String deliverable,
            @RequestParam(value = "billable", required = false) String billable) {

        List<WBSSetup> components = wbsService.buildFilteredActiveWBSTreeByProjectId(projectId, wbsType, activityTypeId,
                activityStatus, deliverable, billable);
        return ResponseEntity.ok(components);
    }

    @PostMapping("/updateHierarchy")
    public ResponseEntity<String> updateHierarchy(
            @RequestParam("draggedItemId") Long draggedItemId,
            @RequestParam("targetId") Long targetId,
            @RequestParam("dropPosition") String dropPosition) {
        try {
            wbsService.updateHierarchy(draggedItemId, targetId, dropPosition);
            return ResponseEntity.ok().body("{\"message\":\"Hierarchy updated successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/component/update")
    public ResponseEntity<?> updateWbsComponent(
            @RequestParam("id") Long id,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "wbsCode", required = false) String wbsCode,
            @RequestParam(value = "wbsType", required = false) String wbsType,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "dueDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueDate,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "actualStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date actualStartDate,
            @RequestParam(value = "actualEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date actualEndDate,
            @RequestParam(value = "assignedTo", required = false) String assignedTo) {
        try {
            WBSSetup updated = wbsService.updateWbsComponent(id, description, wbsCode, wbsType, startDate, dueDate,
                    status, actualStartDate, actualEndDate, assignedTo);
            return ResponseEntity.ok()
                    .body("{\"message\":\"Component updated successfully\", \"id\": " + updated.getId() + "}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/version/save")
    public ResponseEntity<?> saveWbsVersion(
            @RequestParam("projectId") Long projectId,
            @RequestParam("versionName") String versionName,
            @RequestParam("versionNumber") String versionNumber,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "createdBy", required = false) String createdBy) {
        try {
            WBSVersion version = wbsService.saveWbsVersion(projectId, versionName, versionNumber, description,
                    createdBy);
            return ResponseEntity.ok(version);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/version/list")
    public ResponseEntity<List<WBSVersion>> getWbsVersions(@RequestParam("projectId") Long projectId) {
        List<WBSVersion> versions = wbsService.getWbsVersionsByProjectId(projectId);
        return ResponseEntity.ok(versions);
    }

}
