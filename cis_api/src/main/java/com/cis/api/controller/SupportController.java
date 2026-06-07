package com.cis.api.controller;

import com.cis.api.service.SupportService;
import com.cis.api.vo.SupportDashboardVo;
import com.cis.api.vo.TicketResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Support Dashboard and Tickets REST API.
 *
 * Base URL: /cis_api/api/support
 *
 * ─────────────────────────────────────────────────────────
 * DASHBOARD:
 *   GET /cis_api/api/support/dashboard/{projectId}
 *
 * TICKETS:
 *   GET /cis_api/api/support/tickets/{projectId}
 *       ?type=Bug
 *       &userId=5
 *       &severity=High
 *       &status=N
 *       &moduleId=3
 *       &fromDate=2024-01-01
 *       &toDate=2024-12-31
 *       &tag=urgent
 *       &page=1
 *       &maxResult=5
 * ─────────────────────────────────────────────────────────
 */
@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "*")
public class SupportController {

    @Autowired
    private SupportService supportService;

    // SUPPORT DASHBOARD

    /**
     * Returns ticket counts grouped by type (total / open / closed) for a project.
     * 
     *   GET http://localhost:8080/cis_api/api/support/dashboard/186
     */
    @GetMapping("/dashboard/{projectId}")
    public ResponseEntity<SupportDashboardVo> getSupportDashboard(
            @PathVariable("projectId") Integer projectId) {

        SupportDashboardVo response = supportService.getSupportDashboard(projectId);
        return ResponseEntity.ok(response);
    }

    // TICKETS LIST

    /**
     * Returns paginated ticket list with optional filters.
     * Also returns filter metadata (moduleList, tagList, userList) for dropdowns.

     *   GET http://localhost:8080/cis_api/api/support/tickets/186
     *   GET http://localhost:8080/cis_api/api/support/tickets/186?type=Bug&page=1&maxResult=5
     *   GET http://localhost:8080/cis_api/api/support/tickets/186?severity=High&status=N
     *   GET http://localhost:8080/cis_api/api/support/tickets/186?fromDate=2024-01-01&toDate=2024-12-31
     *
     * @param projectId   project id (required, in path)
     * @param type        ticket type e.g. Bug, Complaints, Data Correction, Doubt Clearance, Admin
     * @param userId      filter by user who entered ticket (0 = all)
     * @param severity    e.g. High, Medium, Low
     * @param status      assignStatus value e.g. Y or N
     * @param moduleId    module filter (0 = all)
     * @param fromDate    start date filter (yyyy-MM-dd)
     * @param toDate      end date filter (yyyy-MM-dd)
     * @param tag         tag filter
     * @param page        page number starting from 1 (default 1)
     * @param maxResult   page size (default 5)
     */
    @GetMapping("/tickets/{projectId}")
    public ResponseEntity<TicketResponseVo> getTickets(
            @PathVariable("projectId") Integer projectId,
            @RequestParam(value = "type",      required = false) String type,
            @RequestParam(value = "userId",    required = false, defaultValue = "0") Integer userId,
            @RequestParam(value = "severity",  required = false) String severity,
            @RequestParam(value = "status",    required = false) String status,
            @RequestParam(value = "moduleId",  required = false, defaultValue = "0") Integer moduleId,
            @RequestParam(value = "fromDate",  required = false)
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(value = "toDate",    required = false)
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
            @RequestParam(value = "tag",       required = false) String tag,
            @RequestParam(value = "page",      required = false, defaultValue = "1") int page,
            @RequestParam(value = "maxResult", required = false, defaultValue = "5") int maxResult) {

        TicketResponseVo response = supportService.getTickets(
                projectId, type, userId, severity, status, moduleId,
                fromDate, toDate, tag, page, maxResult);

        return ResponseEntity.ok(response);
    }
}
