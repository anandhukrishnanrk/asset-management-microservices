package com.cis.api.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cis.api.dto.ProjectPrivilegeSaveRequestDto;
import com.cis.api.dto.RtmRowDto;
import com.cis.api.dto.TestCaseDetailsDTO;
import com.cis.api.entity.CrsVerHistory;
import com.cis.api.entity.NewAttachments;
import com.cis.api.entity.ResourceAllocationDetails;
import com.cis.api.entity.TaskAssignments;
import com.cis.api.entity.TestReportResult;
import com.cis.api.entity.PathTraversal;
import com.cis.api.service.ProjectDetailsService;
import com.cis.api.vo.ChangeLogBaseVo;
import com.cis.api.vo.ChangeLogDetailsVo;
import com.cis.api.vo.CisQueriesVo;
import com.cis.api.vo.CmmiKickOffMeetingVo;
import com.cis.api.vo.CmpUploadDetailsVo;
import com.cis.api.vo.CrsVersionVo;
import com.cis.api.vo.DdVo;
import com.cis.api.vo.DddDetailsVo;
import com.cis.api.vo.DeliverySchedule;
import com.cis.api.vo.ImpUploadDetailsVo;
import com.cis.api.vo.LevelOneEstimationVersionVo;
import com.cis.api.vo.MonthlyResourceCountVo;
import com.cis.api.vo.PmpDetailsVo;
import com.cis.api.vo.PmpGdReleasedVo;
import com.cis.api.vo.ProjectDetailsVo;
import com.cis.api.vo.QualityPlanVo;
import com.cis.api.vo.ReleaseUploadDetailsVo;
import com.cis.api.vo.SrsVersionHistoryVo;
import com.cis.api.vo.SrsVersionVo;
import com.cis.api.vo.SupportingDocumentsVo;
import com.cis.api.vo.TestCaseVersionsVo;
import com.cis.api.vo.TestPlanUploadsVo;
import com.cis.api.vo.TestReportResultDto;
import com.cis.api.vo.TestingCountVo;
import com.cis.api.vo.UnitTestResultDto;

@RestController
@RequestMapping("/api/project-details")
@CrossOrigin(origins = "*")
public class ProjectDetailsController {

	@Autowired
	private ProjectDetailsService projectDetailsService;

	@GetMapping("/ping")
	public String ping() {                           
		return "ProjectDetailsController is up!";
	}

	@GetMapping("/projects")
	public Collection<ProjectDetailsVo> getAllProjectNames() {
		return projectDetailsService.getAllProjectNames();
	}

	@GetMapping("/alloted-users/{projectId}")
	public ResponseEntity<Collection<ResourceAllocationDetails>> getAllResourceAllocationDetailsList(
			@PathVariable Integer projectId) {
		Collection<ResourceAllocationDetails> result = projectDetailsService
				.getAllResourceAllocationDetailsList(projectId);
		if (result == null || result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/client/{clientId}")
	public Collection<ProjectDetailsVo> getProjectDetailsByClientId(@PathVariable("clientId") Long clientId) {
		return projectDetailsService.getProjectDetailsByClientId(clientId);
	}

	// *****PLANNING*****//
	@GetMapping("/released-gd/{projectId}")
	public Collection<PmpGdReleasedVo> getReleasedGds(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getReleasedGdsByProjectId(projectId);
	}

	@GetMapping("/kickoff-meeting/{projectId}")
	public CmmiKickOffMeetingVo getKickOffMeetings(@PathVariable("projectId") Long projectId) {
		return projectDetailsService.getKickOffMeetingsByProjectId(projectId);
	}

	@GetMapping("/cmp-details/{projectId}")
	public Collection<CmpUploadDetailsVo> getCmpDetails(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getCmpDetailsByProjectId(projectId);
	}

	@GetMapping("/test-plan/{projectId}")
	public Collection<TestPlanUploadsVo> getTestPlanUploads(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getTestPlanUploads(projectId);
	}

	@GetMapping("/quality-plan/{projectId}")
	public Collection<QualityPlanVo> getQualityPlans(@PathVariable("projectId") Long projectId) {
		return projectDetailsService.getQualityPlansByProjectId(projectId);
	}

	@GetMapping("/imp-details/{projectId}")
	public Collection<ImpUploadDetailsVo> getImpDetails(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getImpDetailsByProjectId(projectId);
	}

	// *****REQUIREMENTS*****//
	@GetMapping("/crs-versions/{projectId}")
	public Collection<CrsVersionVo> getCrsVersions(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getCrsVersionsByProjectId(projectId);
	}

	@GetMapping("/crs-version-history/{crsVer}")
	public Collection<CrsVerHistory> getCrsVersionHistory(@PathVariable Integer crsVer) {
		return projectDetailsService.getCrsVersionshistoryByCrsVerId(crsVer);
	}

	@GetMapping("/srs-versions/{projectId}")
	public Collection<SrsVersionVo> getSrsVersions(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getSrsVersionsByProjectId(projectId);
	}

	@GetMapping("/Srs-list/{srsverid}")
	public List<SrsVersionHistoryVo> getSrsDetailList(@PathVariable("srsverid") Integer srsverid) {
		return projectDetailsService.getSrsDetailListBySrsverid(srsverid);
	}

	@GetMapping("/level-one-estimations/{projectId}")
	public Collection<LevelOneEstimationVersionVo> getLevelOneEstimations(
			@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getLevelOneEstimationsByProjectId(projectId);
	}

	@GetMapping("/dd-details/{projectId}")
	public Collection<DdVo> getDdDetails(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getDdDetailsByProjectId(projectId);
	}

	@GetMapping("/sdd-details/{projectId}")
	public Collection<DddDetailsVo> getDddDetails(@PathVariable("projectId") Long projectId) {
		return projectDetailsService.getDddDetailsByProjectId(projectId);
	}

	@GetMapping("/test-case-versions/{projectId}")
	public Collection<TestCaseVersionsVo> getTestCaseVersions(@PathVariable("projectId") Long projectId) {
		return projectDetailsService.getTestCaseVersionsByProjectId(projectId);
	}

	@GetMapping("/testing-counts/{projectId}")
	public TestingCountVo getTestingCounts(@PathVariable("projectId") Long projectId) {
		return projectDetailsService.getTestingCountsByProjectId(projectId);
	}

	@GetMapping("/unassignedPoint/{cmmiProjectId}/{assignStatus}/{moduleId}/{pageNumber}")
	public Page<SrsVersionHistoryVo> getUnassignedPoints(@PathVariable Long cmmiProjectId,
			@PathVariable String assignStatus, @PathVariable Long moduleId, @PathVariable int pageNumber) {

		return projectDetailsService.getUnassignedPoint(cmmiProjectId, assignStatus, moduleId, pageNumber);
	}

	// @GetMapping("/unitTest-list/{projectId}/{groupId}/{pageNo}")
	// public UnitTestResponseDto getUnitTestList(
	// @PathVariable Integer projectId,
	// @PathVariable Integer groupId,
	// @PathVariable Integer pageNo) {
	//
	// return projectDetailsService.getUnitTestList(projectId, groupId, pageNo);
	// }

	// @GetMapping("/test-report/bugs/{projectId}")
	// public Page<TestReportResultDto> getTestReportBugFilter(
	// @PathVariable Integer projectId,
	// @RequestParam(required = false) Integer moduleId,
	// @RequestParam(required = false) Long userId,
	// @RequestParam(required = false) String taskStatus,
	// @RequestParam(required = false) Integer versionId,
	// @RequestParam(required = false) String types,
	// @RequestParam(required = false) Integer bugTypeId,
	// @RequestParam(defaultValue = "0") int page,
	// @RequestParam(defaultValue = "10") int size
	// ) {
	// return projectDetailsService.filterTestReportBugs(
	// projectId, moduleId, userId, taskStatus, versionId, types, bugTypeId, page,
	// size
	// );
	// }

	//
	// @GetMapping("/userEffortDetails/project/user")
	// public Page<TaskAssignments> getUserEffortDetails(
	// @RequestParam Integer projectId,
	// @RequestParam(required = false) Long userId,
	// @RequestParam String dateStatus,
	// @RequestParam(defaultValue = "0") int page,
	// @RequestParam(defaultValue = "10") int size
	// ) {
	// return projectDetailsService.userEffortDetailsByUserAndDate(
	// projectId, userId, dateStatus, page, size
	// );
	// }
	//
	// @GetMapping("/userEffortDetails/project/user")
	// public Page<TaskAssignments> getUserEffortDetails(
	// @RequestParam("projectId") Integer projectId,
	// // @RequestParam("userId") Long userId,
	// @RequestParam(value = "userId", required = false) Long userId,
	// @RequestParam("dateStatus") String dateStatus,
	// @RequestParam("page") int page,
	// @RequestParam("pageSize") int pageSize) {
	//
	// return projectDetailsService.userEffortDetailsByUserAndDate(projectId,
	// userId, dateStatus, page, pageSize);
	// }

//
//    @GetMapping("/userEffortDetails/project/user")
//    public Page<TaskAssignments> getUserEffortDetails(
//            @RequestParam Integer projectId,
//            @RequestParam(required = false) Long userId,
//            @RequestParam String dateStatus,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return projectDetailsService.userEffortDetailsByUserAndDate(
//                projectId, userId, dateStatus, page, size
//        );
//    }
//    
//    @GetMapping("/userEffortDetails/project/user")
//    public Page<TaskAssignments> getUserEffortDetails(
//            @RequestParam("projectId") Integer projectId,
//    //        @RequestParam("userId") Long userId,
//            @RequestParam(value = "userId", required = false) Long userId,
//            @RequestParam("dateStatus") String dateStatus,
//            @RequestParam("page") int page,
//            @RequestParam("pageSize") int pageSize) {
//
//        return projectDetailsService.userEffortDetailsByUserAndDate(projectId, userId, dateStatus, page, pageSize);
//    }
    
//    @GetMapping("/rtm-details")
//    public Page<RtmRowDto> getRtmDetails(
//            @RequestParam(name = "pId") Integer pId,
//            @RequestParam(name = "gId") Integer gId,
//            @RequestParam(name = "page", defaultValue = "0") Integer page,
//            @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize
//    ) {
//        return projectDetailsService.rtmDetailsByPidAndGid(
//                pId, gId, page, pageSize
//        );
//    }
    
    
    
//    @GetMapping("/rtm-details")
//    public Page<RtmRowDto> getRtmDetails(
//            @RequestParam(name = "pId") Long pId,
//            @RequestParam(name = "gId") Integer gId,
//            @RequestParam(name = "status") String status,  // added
//            @RequestParam(name = "page", defaultValue = "0") Integer page,
//            @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize
//    ) {
//        return projectDetailsService.rtmDetailsByPidAndGid(
//                pId, gId, status, page, pageSize
//        );
//    }
//    
//    
//    @GetMapping("/testcase-details")
//    public Page<TestCaseDetailsDTO> getRtmDetails(
//            @RequestParam(name = "project_Id") Integer projectId,
//            @RequestParam(name = "tstCaseVerId") Integer tstCaseVerId,
//            @RequestParam(name = "srsVerId", required = false) Integer srsVerId, // can ignore
//            @RequestParam(name = "page", defaultValue = "0") Integer page,
//            @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize
//    ) {
//        return projectDetailsService.getTestCaseDetailsByVersion(
//                projectId,
//                tstCaseVerId,
//                srsVerId,
//                page,
//                pageSize
//        );
//    }
//    
//    
//    @GetMapping("/pnp-details")
//    public PmpDetailsVo getPmpDetails(
//            @RequestParam("pmpId") Integer pmpId,
//            @RequestParam("substatus") String substatus,
//            @RequestParam("approvestatus") String approvestatus,
//            @RequestParam("projectId") Integer projectId
//    ) {
//        return projectDetailsService.getPmpDetails(
//                pmpId, substatus, approvestatus, projectId
//        );
//    }
    
    
    	
    
    
    
    
	// @GetMapping("/rtm-details")
	// public Page<RtmRowDto> getRtmDetails(
	// @RequestParam(name = "pId") Integer pId,
	// @RequestParam(name = "gId") Integer gId,
	// @RequestParam(name = "page", defaultValue = "0") Integer page,
	// @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize
	// ) {
	// return projectDetailsService.rtmDetailsByPidAndGid(
	// pId, gId, page, pageSize
	// );
	// }

	@GetMapping("/rtm-details")
	public Page<RtmRowDto> getRtmDetails(@RequestParam(name = "pId") Long pId,
			@RequestParam(name = "gId") Integer gId, @RequestParam(name = "status") String status, // added
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize) {
		return projectDetailsService.rtmDetailsByPidAndGid(pId, gId, status, page, pageSize);
	}

	@GetMapping("/testcase-details")
	public Page<TestCaseDetailsDTO> getRtmDetails(@RequestParam(name = "project_Id") Integer projectId,
			@RequestParam(name = "tstCaseVerId") Integer tstCaseVerId,
			@RequestParam(name = "srsVerId", required = false) Integer srsVerId, // can ignore
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize) {
		return projectDetailsService.getTestCaseDetailsByVersion(projectId, tstCaseVerId, srsVerId, page, pageSize);
	}

	@GetMapping("/pmp-details")
	public PmpDetailsVo getPmpDetails(@RequestParam("pmpId") Integer pmpId, @RequestParam("substatus") String substatus,
			@RequestParam("approvestatus") String approvestatus, @RequestParam("projectId") Integer projectId) {
		return projectDetailsService.getPmpDetails(pmpId, substatus, approvestatus, projectId);
	}

	@GetMapping("/unitTest-list/{projectId}/{page}")
	public Page<UnitTestResultDto> getUnitTestList(@PathVariable Integer projectId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return projectDetailsService.getUnitTestList(projectId, page, size);
	}

	@GetMapping("/supporting-documents/{projectId}")
	public Collection<SupportingDocumentsVo> getSupportingDocuments(@PathVariable("projectId") Long projectId) {
		return projectDetailsService.getSupportingDocumentsByCmmiProjectId(projectId);
	}

	@GetMapping("/deliverables/{projectId}")
	public Collection<DeliverySchedule> getDeliverableSchedules(@PathVariable("projectId") Integer projectId) {
		return projectDetailsService.getDeliverableSchedulesByProjectId(projectId);
	}

	@GetMapping("/release-notes/{projectId}")
	public Page<ReleaseUploadDetailsVo> getReleaseNotes(@PathVariable("projectId") Long projectId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return projectDetailsService.getReleaseUploadDetailsByProjectIdPaged(projectId, page, size);
	}

	@GetMapping("/change-logs/{projectId}")
	public Page<ChangeLogBaseVo> getChangeLogs(@PathVariable("projectId") Long projectId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return projectDetailsService.getChangeLogByProjectIdPaged(projectId, page, size);
	}

	@GetMapping("/change-log-details/{baseId}")
	public Page<ChangeLogDetailsVo> getChangeLogDetails(@PathVariable("baseId") Long baseId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return projectDetailsService.getChangeLogDetailsByBaseIdPaged(baseId, page, size);
	}

	@GetMapping("/Performance-Testing/{cmmiProjectId}/{pageNo}")
	public Page<NewAttachments> getPerfomanceTestingDetails(@PathVariable Long cmmiProjectId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return projectDetailsService.getPerfomanceTestingDetailsByCmmiProjectId(cmmiProjectId, page, size);
	}

	@GetMapping("/bugReport/{pid}/{pageNo}")
	public Page<TestReportResult> getBugReport(@PathVariable Integer pid, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return projectDetailsService.getBugReportDetails(pid, page, size);
	}

	@GetMapping("/filterUnitTests/{projectId}")
	public Page<TestReportResult> filterUnitTests(@PathVariable Integer projectId,
			@RequestParam(required = false) Integer moduleId, @RequestParam(required = false) Integer userId,
			@RequestParam(required = false) String taskStatus, @RequestParam(required = false) Integer bugTypeId,
			@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size // <-- ADDED
	) {
		return projectDetailsService.filterUnitTestBugs(projectId, moduleId, userId, taskStatus, bugTypeId, fromDate,
				toDate, page);
	}

	@GetMapping("/test-report/bugs/{projectId}")
	public Page<TestReportResultDto> getTestReportBugFilter(@PathVariable Integer projectId,
			@RequestParam(required = false) Integer moduleId, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) String taskStatus, @RequestParam(required = false) Integer versionId,
			@RequestParam(required = false) String types, @RequestParam(required = false) Integer bugTypeId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return projectDetailsService.filterTestReportBugs(projectId, moduleId, userId, taskStatus, versionId, types,
				bugTypeId, page, size);
	}

	@GetMapping("/userEffortDetails/project/user")
	public Page<TaskAssignments> getUserEffortDetails(@RequestParam Integer projectId, @RequestParam Long userId,
			@RequestParam String dateStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return projectDetailsService.userEffortDetailsByUserAndDate(projectId, userId, dateStatus, page, size);
	}

	@PostMapping(value = "/query", consumes = { org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public String saveQuery(@org.springframework.web.bind.annotation.RequestPart("data") String data,
			@org.springframework.web.bind.annotation.RequestPart(value = "file", required = false) org.springframework.web.multipart.MultipartFile file) {

		com.google.gson.Gson gson = new com.google.gson.Gson();
		CisQueriesVo cisQueriesVo = gson.fromJson(data, CisQueriesVo.class);
		return projectDetailsService.saveQuery(cisQueriesVo, file);
	}

	@GetMapping("/queries")
	public List<CisQueriesVo> getQueries(@RequestParam(required = false) Integer projectId,
			@RequestParam(required = false) Integer moduleId, @RequestParam(required = false) String tabCode,
			@RequestParam(required = false) Integer linkId) {
		return projectDetailsService.getQueries(projectId, moduleId, tabCode, linkId);
	}

	@GetMapping("/download/{fileName:.+}")
	public org.springframework.http.ResponseEntity<org.springframework.core.io.Resource> downloadFile(
			@PathVariable String fileName, jakarta.servlet.http.HttpServletRequest request) {
		return projectDetailsService.downloadQueryAttachment(fileName, request);
	}
	
	/**
	 * GET /api/project-details/{projectId}/resource-count-by-month?year=2026
	 * GET /api/project-details/42/resources-by-month?year=2026&month=2
	 * Returns monthly count for the bar graph.
	 * Response: [ { "year":2026, "month":1, "monthName":"Jan", "count":10 }, ... ]
	 */
	@GetMapping("/{projectId}/resource-count-by-month")
	public ResponseEntity<List<MonthlyResourceCountVo>> getMonthlyResourceCount(
	        @PathVariable Integer projectId,
	        @RequestParam(required = false) Integer year) {

	    List<MonthlyResourceCountVo> result =
	            projectDetailsService.getMonthlyResourceCount(projectId, year);

	    if (result == null || result.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(result);
	}

	/**
	 * GET /api/projects/{projectId}/resources-by-month?year=2026&month=2
	 *
	 * Returns full resource details for the clicked bar (drill-down).
	 * Reuses existing ResourceAllocationDetails response shape.
	 */
	@GetMapping("/{projectId}/resources-by-month")
	public ResponseEntity<Collection<ResourceAllocationDetails>> getResourcesByMonth(
	        @PathVariable Integer projectId,
	        @RequestParam int year,
	        @RequestParam int month) {

	    Collection<ResourceAllocationDetails> result =
	            projectDetailsService.getResourcesByMonth(projectId, year, month);

	    if (result == null || result.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(result);
	}	

	@PostMapping("/save-role-privileges")
	public ResponseEntity<String> saveRolePrivileges(
			@RequestBody ProjectPrivilegeSaveRequestDto request) {
		try {
			String result = projectDetailsService.saveProjectRolePrivileges(request);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error saving privileges: " + e.getMessage());
		}
	}

	@GetMapping("/path-traversals/{projectId}")
	public ResponseEntity<List<PathTraversal>> getPathTraversals(@PathVariable("projectId") Long projectId) {
		List<PathTraversal> traversals = projectDetailsService.getPathTraversalsByProjectId(projectId);
		if (traversals == null || traversals.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(traversals);
	}

	@GetMapping("/role-privileges/{projectId}")
	public ResponseEntity<List<com.cis.api.entity.CisProjectRolePrivileges>> getRolePrivileges(
			@PathVariable Long projectId) {
		return ResponseEntity.ok(projectDetailsService.getProjectRolePrivileges(projectId));
	}

}
