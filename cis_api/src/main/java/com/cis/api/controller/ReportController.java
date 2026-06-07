package com.cis.api.controller;

import java.util.Collection;

import com.cis.api.service.ProjectDetailsService;
import com.cis.api.service.ReportService;
import com.cis.api.vo.DeliverySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ProjectDetailsService projectDetailsService;

    @GetMapping("/deliverables/{projectId}")
    public Collection<DeliverySchedule> getDeliverableSchedules(@PathVariable("projectId") Integer projectId) {
        return projectDetailsService.getDeliverableSchedulesByProjectId(projectId);
    }

    // @GetMapping("/project/{projectId}")
    // public ResponseEntity<List<ReportVo>> getReportsByProjectId(
    // @PathVariable("projectId") Long projectId) {
    // List<ReportVo> reports = reportService.getReportsByProjectId(projectId);
    // return ResponseEntity.ok(reports);
    // }
}
