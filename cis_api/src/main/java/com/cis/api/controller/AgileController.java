package com.cis.api.controller;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.cis.api.vo.CisQueriesVo;

import com.cis.api.dto.RtmDisplayDto;
import com.cis.api.dto.RtmRowDto;
import com.cis.api.dto.TestCaseDetailsDTO;
import com.cis.api.entity.CrsVerHistory;
import com.cis.api.entity.NewAttachments;
import com.cis.api.entity.PmpGdReleased;
import com.cis.api.entity.ResourceAllocationDetails;
import com.cis.api.entity.ResourceRequest;
import com.cis.api.entity.SprintDetails;
import com.cis.api.entity.TaskAssignments;
import com.cis.api.entity.TestCaseDetailsVersionHistory;
import com.cis.api.entity.TestReportResult;
import com.cis.api.service.AgileDetailsService;
import com.cis.api.service.ProjectDetailsService;
import com.cis.api.vo.ChangeLogBaseVo;
import com.cis.api.vo.ChangeLogDetailsVo;
import com.cis.api.vo.CmmiKickOffMeetingVo;
import com.cis.api.vo.CmpUploadDetailsVo;
import com.cis.api.vo.CrsVersionVo;
import com.cis.api.vo.DdVo;
import com.cis.api.vo.DddDetailsVo;
import com.cis.api.vo.DeliverySchedule;
import com.cis.api.vo.ImpUploadDetailsVo;
import com.cis.api.vo.LevelOneEstimationVersionVo;
import com.cis.api.vo.PmpDetailsVo;
import com.cis.api.vo.PmpGdReleasedVo;
import com.cis.api.vo.ProjectDetailsVo;
import com.cis.api.vo.QualityPlanVo;
import com.cis.api.vo.ReleaseUploadDetailsVo;
import com.cis.api.vo.SrsPointSprintVo;
import com.cis.api.vo.SrsVersionHistoryVo;
import com.cis.api.vo.SrsVersionVo;
import com.cis.api.vo.SupportingDocumentsVo;
import com.cis.api.vo.TestCaseVersionsVo;
import com.cis.api.vo.TestPlanUploadsVo;
import com.cis.api.vo.TestReportResultDto;
import com.cis.api.vo.TestingCountVo;
import com.cis.api.vo.UnitTestResultDto;

@RestController
@RequestMapping("/api/agile-details")
@CrossOrigin(origins = "*")
public class AgileController {

	@Autowired AgileDetailsService agileDetailsService;
	
	@GetMapping("/ping")
	public String ping() {
		return "ProjectDetailsController is up!";
	}
	
	@GetMapping("/test")
	public String test() {
		return "ProjectDetailsController is up!";
	}
	
	@GetMapping("/sprint-details")
    public List<SprintDetails> getAgileDetails(
            @RequestParam(name = "projectId") Integer projectId,
            @RequestParam(name = "moduleId", required = false) Integer moduleId,
            @RequestParam(name = "assignStatus", required = false) String assignStatus
    ) {
        return agileDetailsService.getSprintDetails(projectId, moduleId, assignStatus);
    }
	
	
	
	
	@GetMapping("/assigned-srs-points-sprint")
	public Page<SrsPointSprintVo> getAllAssignedSrsPoint(
	        @RequestParam(name = "projectId") Integer projectId,
	        @RequestParam(name = "moduleId", required = false) Integer moduleId,
	        @RequestParam(name = "assignStatus", required = false) String assignStatus,
	        @RequestParam(name = "sprintName", required = false) String sprintName,
	        @RequestParam(name = "sprintId") Integer sprintId,
	        @RequestParam(name = "searchTerm", required = false) String searchTerm,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "15") int size
	) {
	    return agileDetailsService.getAssignedSrsPoint(
	            projectId, moduleId, assignStatus, sprintName, sprintId, searchTerm,
	            page, size
	    );
	}
	
	
	@GetMapping("/assigned-srs-points")
	public Page<SrsPointSprintVo> getAssigneSrsPointCoding(
	        @RequestParam(name = "projectId") Integer projectId,
	        @RequestParam(name = "moduleId", required = false) Integer moduleId,
	        @RequestParam(name = "assignStatus", required = false) String assignStatus,
	        @RequestParam(name = "searchTerm", required = false) String searchTerm,
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "15") int size
	) {
	    return agileDetailsService.getAssignedSrsPointCoding(
	            projectId, moduleId, assignStatus, searchTerm,
	            page, size
	    );
	}
}

