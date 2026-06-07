package com.cis.api.service;

import com.cis.api.repository.SupportRepository;
import com.cis.api.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SupportService {

    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private SupportRepository supportRepository;

    // SUPPORT DASHBOARD

    /**
     * @param projectId required
     */
    public SupportDashboardVo getSupportDashboard(Integer projectId) {

        SupportDashboardVo vo = new SupportDashboardVo();
        vo.setProjectId(projectId);
        vo.setProjectName(supportRepository.getProjectNameByProjectId(projectId));

        // ── Total counts ────────────────────────────────────────────────────
        vo.setCountAdditionModification(
                supportRepository.countByTypeAndStatus(projectId, "Modification", null) +
                supportRepository.countByTypeAndStatus(projectId, "New Suggestion", null));
        vo.setCountBug(supportRepository.countByTypeAndStatus(projectId, "Bug", null));
        vo.setCountDataCorrection(supportRepository.countByTypeAndStatus(projectId, "Data Correction", null));
        vo.setCountDoubtClearance(supportRepository.countByTypeAndStatus(projectId, "Doubt Clearance", null));
        vo.setCountComplaints(supportRepository.countByTypeAndStatus(projectId, "Complaints", null));
        vo.setCountAdmin(supportRepository.countByTypeAndStatus(projectId, "Admin", null));

        // ── Closed counts (closeStatus = 'Y') ────────────────────────────────
        vo.setClosedCountAdditionModification(
                supportRepository.countByTypeAndStatus(projectId, "Modification", "Y") +
                supportRepository.countByTypeAndStatus(projectId, "New Suggestion", "Y"));
        vo.setClosedCountBug(supportRepository.countByTypeAndStatus(projectId, "Bug", "Y"));
        vo.setClosedCountDataCorrection(supportRepository.countByTypeAndStatus(projectId, "Data Correction", "Y"));
        vo.setClosedCountDoubtClearance(supportRepository.countByTypeAndStatus(projectId, "Doubt Clearance", "Y"));
        vo.setClosedCountComplaints(supportRepository.countByTypeAndStatus(projectId, "Complaints", "Y"));
        vo.setClosedCountAdmin(supportRepository.countByTypeAndStatus(projectId, "Admin", "Y"));

        // ── Open counts (closeStatus = 'N') ──────────────────────────────────
        vo.setOpenCountAdditionModification(
                supportRepository.countByTypeAndStatus(projectId, "Modification", "N") +
                supportRepository.countByTypeAndStatus(projectId, "New Suggestion", "N"));
        vo.setOpenCountBug(supportRepository.countByTypeAndStatus(projectId, "Bug", "N"));
        vo.setOpenCountDataCorrection(supportRepository.countByTypeAndStatus(projectId, "Data Correction", "N"));
        vo.setOpenCountDoubtClearance(supportRepository.countByTypeAndStatus(projectId, "Doubt Clearance", "N"));
        vo.setOpenCountComplaints(supportRepository.countByTypeAndStatus(projectId, "Complaints", "N"));
        vo.setOpenCountAdmin(supportRepository.countByTypeAndStatus(projectId, "Admin", "N"));

        // ── Grand totals ────────────────────────────────────────────────────
        long total = vo.getCountAdditionModification() + vo.getCountBug()
                   + vo.getCountDataCorrection() + vo.getCountDoubtClearance()
                   + vo.getCountComplaints() + vo.getCountAdmin();

        long totalClosed = vo.getClosedCountAdditionModification() + vo.getClosedCountBug()
                         + vo.getClosedCountDataCorrection() + vo.getClosedCountDoubtClearance()
                         + vo.getClosedCountComplaints() + vo.getClosedCountAdmin();

        long totalOpen = vo.getOpenCountAdditionModification() + vo.getOpenCountBug()
                       + vo.getOpenCountDataCorrection() + vo.getOpenCountDoubtClearance()
                       + vo.getOpenCountComplaints() + vo.getOpenCountAdmin();

        vo.setTotalCount(total);
        vo.setTotalClosed(totalClosed);
        vo.setTotalOpen(totalOpen);

        return vo;
    }

    // =========================================================================
    // TICKETS LIST (paginated)
    // =========================================================================

    /**
     * Mirrors gotoTickets() — returns paginated ticket list with filter metadata.
     *
     * @param projectId  required
     * @param type       optional — ticket type filter
     * @param userId     optional — 0 = all users
     * @param severity   optional
     * @param status     optional — maps to assignStatus
     * @param moduleId   optional — 0 = all modules
     * @param fromDate   optional
     * @param toDate     optional
     * @param tag        optional
     * @param page       page number starting from 1 (default 1)
     * @param maxResult  page size (default 5, same as original)
     */
    public TicketResponseVo getTickets(Integer projectId, String type, Integer userId,
                                       String severity, String status, Integer moduleId,
                                       Date fromDate, Date toDate, String tag,
                                       int page, int maxResult) {

        if (userId == null) userId = 0;
        if (moduleId == null) moduleId = 0;
        if (maxResult <= 0) maxResult = DEFAULT_PAGE_SIZE;
        if (page <= 0) page = 1;

        int firstResult = (page - 1) * maxResult;

        TicketResponseVo response = new TicketResponseVo();
        response.setProjectId(projectId);
        response.setFirstResult(firstResult);
        response.setMaxResult(maxResult);
        response.setCurrentPage(page);

        // ── Total count for pagination ───────────────────────────────────────
        long totalCount = supportRepository.countTickets(
                projectId, type, userId, severity, status, moduleId, fromDate, toDate, tag);
        response.setTotalCount(totalCount);
        response.setTotalPages((int) Math.ceil((double) totalCount / maxResult));

        // ── Ticket list ──────────────────────────────────────────────────────
        List<TicketVo> tickets = supportRepository.getTickets(
                projectId, firstResult, maxResult,
                type, userId, severity, status, moduleId, fromDate, toDate, tag);

        response.setTickets(tickets != null ? tickets : new ArrayList<>());

        // ── Filter metadata (dropdowns) ──────────────────────────────────────
        response.setModuleList(supportRepository.getModulesByProjectId(projectId));
        response.setTagList(supportRepository.getTagsByProjectId(projectId));
        response.setUserList(supportRepository.getUsersByProjectId(projectId));

        return response;
    }
}