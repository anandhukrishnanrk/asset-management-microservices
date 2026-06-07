package com.cis.api.service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cis.api.dto.RtmDisplayDto;
import com.cis.api.dto.RtmRowDto;
import com.cis.api.dto.TestCaseDetailsDTO;
import com.cis.api.entity.ChangeLogBase;
import com.cis.api.entity.CisQueries;
import com.cis.api.repository.CisQueriesRepository;
import com.cis.api.vo.CisQueriesVo;
import com.cis.api.entity.ChangeLogDetails;
import com.cis.api.entity.CmmiProject;

import com.cis.api.entity.CmmiProjectResources;

import com.cis.api.entity.CmmiKickOffMeeting;
import com.cis.api.entity.CmpUploadDetails;
import com.cis.api.entity.Crs;
import com.cis.api.entity.CrsVerHistory;
import com.cis.api.entity.CrsVersion;
import com.cis.api.entity.Dd;
import com.cis.api.entity.DddDetails;
import com.cis.api.entity.DddUploadDetails;
import com.cis.api.entity.DeliverableBillingDetails;
import com.cis.api.entity.DeliverableSchedules;
import com.cis.api.entity.DeliverableSchedulesDetails;
import com.cis.api.entity.ImpUploadDetails;
import com.cis.api.entity.LevelOneEstimationVersion;
import com.cis.api.entity.ModuleSetup;
import com.cis.api.entity.NewAttachments;
import com.cis.api.entity.PMPEffortQuantification;
import com.cis.api.entity.PMPResourceAllocation;
import com.cis.api.entity.PmpAssumptions;
import com.cis.api.entity.PmpDependencies;
import com.cis.api.entity.PmpDevelopmentInfrastructure;
import com.cis.api.entity.PmpGdReleased;
import com.cis.api.entity.PmpGeneralDetails;
import com.cis.api.entity.PmpMeasurementPlan;
import com.cis.api.entity.PmpProjectEscalations;
import com.cis.api.entity.PmpReusables;
import com.cis.api.entity.PmpTrainingNeeds;
import com.cis.api.entity.PointsofContact;
import com.cis.api.entity.ProjectDetails;
import com.cis.api.entity.QualityPlan;
import com.cis.api.entity.ReleaseUploadDetails;
import com.cis.api.entity.ResourceAllocationDetails;
import com.cis.api.entity.SDDEntries;
import com.cis.api.entity.SeatUserAlloted;
import com.cis.api.entity.SprintDetails;
import com.cis.api.entity.SprintHistoryDetails;
import com.cis.api.entity.Srs;
import com.cis.api.entity.ResourceRequest;
import com.cis.api.entity.SrsVersion;
import com.cis.api.entity.SrsVersionHistory;
import com.cis.api.entity.SubModuleSetup;
import com.cis.api.entity.SupportingDocuments;
import com.cis.api.entity.TaskAssignments;
import com.cis.api.entity.TechDetails;
import com.cis.api.entity.TestCaseDetails;
import com.cis.api.entity.TestCaseDetailsVersionHistory;
import com.cis.api.entity.TestCaseVersions;
import com.cis.api.entity.TestPlanUploads;
import com.cis.api.entity.TestReportResult;
import com.cis.api.entity.UserSetup;
import com.cis.api.repository.BugTypeRepository;
import com.cis.api.repository.ChangeLogBaseRepository;
import com.cis.api.repository.ChangeLogDetailsRepository;
import com.cis.api.repository.CmmiProjectResourcesRepository;
import com.cis.api.repository.CmmiKickOffMeetingRepository;
import com.cis.api.repository.CmpUploadDetailsRepository;
import com.cis.api.repository.CrsRepository;
import com.cis.api.repository.CrsVerHistoryRepository;
import com.cis.api.repository.CrsVersionRepository;
import com.cis.api.repository.DdRepository;
import com.cis.api.repository.DddDetailsRepository;
import com.cis.api.repository.DddUploadDetailsRepository;
import com.cis.api.repository.DeliverableBillingDetailsRepository;
import com.cis.api.repository.DeliverableSchedulesDetailsRepository;
import com.cis.api.repository.DeliverableSchedulesRepository;
import com.cis.api.repository.GroupsRepository;
import com.cis.api.repository.ImpUploadDetailsRepository;
import com.cis.api.repository.LevelOneEstimationVersionRepository;
import com.cis.api.repository.ModuleSetupRepository;
import com.cis.api.repository.NewAttachmentsRepository;
import com.cis.api.repository.PMPEffortQuantificationRepository;
import com.cis.api.repository.PMPResourceAllocationRepository;
import com.cis.api.repository.PmpAssumptionsRepository;
import com.cis.api.repository.PmpDependenciesRepository;
import com.cis.api.repository.PmpDevelopmentInfrastructureRepository;
import com.cis.api.repository.PmpGdReleasedRepository;
import com.cis.api.repository.PmpMeasurementPlanRepository;
import com.cis.api.repository.PmpProjectEscalationsRepository;
import com.cis.api.repository.PmpReusablesRepository;
import com.cis.api.repository.PmpTrainingNeedsRepository;
import com.cis.api.repository.PointsofContactRepository;
import com.cis.api.repository.ProjectDetailsRepository;
import com.cis.api.repository.QualityPlanRepository;
import com.cis.api.repository.ReleaseUploadDetailsRepository;
import com.cis.api.repository.ResourceAllocationDetailsRepository;
import com.cis.api.repository.SDDEntriesRepository;
import com.cis.api.repository.ResourceRequestRepository;
import com.cis.api.repository.SeatUserAllotedRepository;
import com.cis.api.repository.SprintDetailsRepository;
import com.cis.api.repository.SprintHistoryDetailsRepository;
import com.cis.api.repository.SrsRepository;
import com.cis.api.repository.SrsVersionHistoryRepository;
import com.cis.api.repository.SrsVersionRepository;
import com.cis.api.repository.SubModuleSetupRepository;
import com.cis.api.repository.SupportingDocumentsRepository;
import com.cis.api.repository.TaskAssignmentsRepository;
import com.cis.api.repository.TechdetailsRepository;
import com.cis.api.repository.TestCaseDetailsRepository;
import com.cis.api.repository.TestCaseDetailsVersionHistoryRepository;
import com.cis.api.repository.TestCaseVersionsRepository;
import com.cis.api.repository.TestPlanUploadsRepository;
import com.cis.api.repository.TestReportHistoryRepository;
import com.cis.api.repository.TestReportResultRepository;
import com.cis.api.repository.TestReportTaskDetailsRepository;
import com.cis.api.repository.UserRepository;
import com.cis.api.repository.UserSetupRepository;
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
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class AgileDetailsService {

    @Autowired
    private SprintDetailsRepository sprintDetailsRepository;
    
    @Autowired
    private ModuleSetupRepository moduleSetupRepository;
    
    @Autowired
    private SrsVersionRepository srsVersionRepository;
    
    @Autowired
    private SprintHistoryDetailsRepository sprintHistoryDetailsRepository;

    public List<SprintDetails> getSprintDetails(Integer projectId, Integer moduleId, String assignStatus) {

        // For now, you only have findByProjectId
        // Optional filters can be added later

        return sprintDetailsRepository.findByProjectId(projectId);
    }

    @Autowired
    private SrsVersionHistoryRepository srsVersionHistoryRepository;

    public Page<SrsPointSprintVo> getAssignedSrsPoint(
            Integer projectId,
            Integer moduleId,
            String assignStatus,
            String sprintName,
            Integer sprintId,
            String searchTerm,
            int page,
            int size
    ) {

        if (assignStatus == null || assignStatus.isBlank()) {
            assignStatus = "UA";
        }

        List<ModuleSetup> moduleListForProject = Collections.emptyList();
        if (projectId != null) {
            moduleListForProject =
                    moduleSetupRepository.findByProject_ProjectIdAndDeleteStatus(
                            projectId.longValue(), sprintName
                    );
        }

        SrsVersion latestVersion =
                srsVersionRepository.getLatestSrsVersion(projectId.longValue());

        List<SprintHistoryDetails> sprintHistoryList =
                sprintId != null
                        ? sprintHistoryDetailsRepository.getSprintHistorylistBySprintId(sprintId)
                        : new ArrayList<>();

        List<String> srsNoList = sprintHistoryList.stream()
                .map(SprintHistoryDetails::getSrsNo)
                .filter(Objects::nonNull)
                .toList();

        if (srsNoList.isEmpty()) {
            return Page.empty();
        }

        Pageable pageable = PageRequest.of(page, size);

        String formattedSearch = (searchTerm == null || searchTerm.isBlank())
                ? null
                : "%" + searchTerm.trim() + "%";

        Page<SrsVersionHistory> historyPage =
                srsVersionHistoryRepository.getSrsVersionHistoryList(
                        latestVersion.getSrsVersionId(),
                        srsNoList,
                        assignStatus,
                        searchTerm,
                        formattedSearch,
                        moduleId,
                        pageable
                );

        List<SrsPointSprintVo> voList = historyPage.stream()
                .map(SrsPointSprintVo::new)
                .toList();

        return new PageImpl<>(voList, pageable, historyPage.getTotalElements());
    }
    
    
    public Page<SrsPointSprintVo> getAssignedSrsPointCoding(
            Integer projectId,
            Integer moduleId,
            String assignStatus,
            String searchTerm,
            int page,
            int size
    ) {

        if (assignStatus == null || assignStatus.isBlank()) {
            assignStatus = "UA";
        }

        List<ModuleSetup> moduleListForProject = Collections.emptyList();
        if (projectId != null) {
            moduleListForProject =
					moduleSetupRepository.findByProject_ProjectIdAndDeleteStatus(
                            projectId.longValue(), searchTerm
                    );
        }

        SrsVersion latestVersion =
                srsVersionRepository.getLatestSrsVersion(projectId.longValue());

        

        Pageable pageable = PageRequest.of(page, size);

        String formattedSearch = (searchTerm == null || searchTerm.isBlank())
                ? null
                : "%" + searchTerm.trim() + "%";

        Page<SrsVersionHistory> historyPage =
                srsVersionHistoryRepository.getSrsVersionHistoryListcoding(
                        latestVersion.getSrsVersionId(),
                        
                        assignStatus,
                        searchTerm,
                        formattedSearch,
                        moduleId,
                        pageable
                );

        List<SrsPointSprintVo> voList = historyPage.stream()
                .map(SrsPointSprintVo::new)
                .toList();

        return new PageImpl<>(voList, pageable, historyPage.getTotalElements());
    }
}