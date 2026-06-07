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
import java.util.Optional;
import java.util.stream.Collectors;

import com.cis.api.entity.PathTraversal;
import com.cis.api.repository.PathTraversalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cis.api.dto.ProjectPrivilegeSaveRequestDto;
import com.cis.api.dto.RtmRowDto;
import com.cis.api.dto.TestCaseDetailsDTO;
import com.cis.api.entity.ChangeLogBase;
import com.cis.api.entity.ChangeLogDetails;
import com.cis.api.entity.CisProjectRolePrivileges;
import com.cis.api.entity.CisQueries;
import com.cis.api.entity.CmmiKickOffMeeting;
import com.cis.api.entity.CmmiProjectResources;
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
import com.cis.api.entity.PmpMeasurementPlan;
import com.cis.api.entity.PmpProjectEscalations;
import com.cis.api.entity.PmpReusables;
import com.cis.api.entity.PmpTrainingNeeds;
import com.cis.api.entity.ProjectDetails;
import com.cis.api.entity.QualityPlan;
import com.cis.api.entity.ReleaseUploadDetails;
import com.cis.api.entity.ResourceAllocationDetails;
import com.cis.api.entity.SDDEntries;
import com.cis.api.entity.SeatUserAlloted;
import com.cis.api.entity.Srs;
import com.cis.api.entity.SrsVersion;
import com.cis.api.entity.SubModuleSetup;
import com.cis.api.entity.SupportingDocuments;
import com.cis.api.entity.TaskAssignments;
import com.cis.api.entity.TestCaseDetails;
import com.cis.api.entity.TestCaseVersions;
import com.cis.api.entity.TestPlanUploads;
import com.cis.api.entity.TestReportResult;
import com.cis.api.repository.BugTypeRepository;
import com.cis.api.repository.ChangeLogBaseRepository;
import com.cis.api.repository.ChangeLogDetailsRepository;
import com.cis.api.repository.CisProjectRolePrivilegesRepository;
import com.cis.api.repository.CisQueriesRepository;
import com.cis.api.repository.CmmiKickOffMeetingRepository;
import com.cis.api.repository.CmmiProjectResourcesRepository;
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
import com.cis.api.repository.ResourceRequestRepository;
import com.cis.api.repository.SDDEntriesRepository;
import com.cis.api.repository.SeatUserAllotedRepository;
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
public class ProjectDetailsService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ProjectDetailsRepository projectDetailsRepository;

	@Autowired
	private CisProjectRolePrivilegesRepository cisProjectRolePrivilegesRepository;

	@Autowired
	TestReportHistoryRepository testReportHistoryRepository;

	@Autowired
	TestReportTaskDetailsRepository testReportTaskDetailsRepository;

	@Autowired
	private CmmiProjectRepository cmmiProjectRepository;

	@Autowired
	private CmmiKickOffMeetingRepository cmmiKickOffMeetingRepository;

	@Autowired
	private PmpGdReleasedRepository pmpGdReleasedRepository;

	@Autowired
	CrsRepository crsRepository;

	@Autowired
	private CmpUploadDetailsRepository cmpUploadDetailsRepository;

	@Autowired
	private TestPlanUploadsRepository testPlanUploadsRepository;

	@Autowired
	private QualityPlanRepository qualityPlanRepository;

	@Autowired
	private ImpUploadDetailsRepository impUploadDetailsRepository;

	@Autowired
	private DeliverableSchedulesRepository deliverableSchedulesRepository;

	@Autowired
	private ResourceAllocationDetailsRepository resourceAllocationDetailsRepository;
	@Autowired
	private CrsVersionRepository crsVersionRepository;

	@Autowired
	private CrsVerHistoryRepository crsVerHistoryRepository;

	@Autowired
	private SrsVersionRepository srsVersionRepository;

	@Autowired

	private SrsRepository srsRepository;

	@Autowired
	private SDDEntriesRepository sDDEntriesRepository;

	@Autowired
	private TestCaseDetailsRepository testCaseDetailsRepository;

	@Autowired
	private TestReportResultRepository testReportResultRepository;

	@Autowired
	private PmpAssumptionsRepository pmpAssumptionsRepository;

	@Autowired
	private ModuleSetupRepository moduleSetupRepository;

	@Autowired
	private BugTypeRepository bugTypeRepository;

	@Autowired
	private SeatUserAllotedRepository seatUserAllotedRepository;

	// private TestReportResultRepository testReportResultRepository;
	//
	// @Autowired
	// private ModuleSetupRepository moduleSetupRepository;
	//
	// @Autowired
	// private BugTypeRepository bugTypeRepository;
	//
	// @Autowired
	// private SeatUserAllotedRepository seatUserAllotedRepository;

	@Autowired
	private SrsVersionHistoryRepository srsVersionHistoryRepository;

	@Autowired
	private PmpDependenciesRepository pmpDependenciesRepository;

	@Autowired
	TaskAssignmentsRepository taskAssignmentsRepository;

	@Autowired
	private LevelOneEstimationVersionRepository levelOneEstimationVersionRepository;

	@Autowired
	private DdRepository ddRepository;

	@Autowired
	private DddDetailsRepository dddDetailsRepository;

	@Autowired
	private DddUploadDetailsRepository dddUploadDetailsRepository;

	@Autowired
	private ReleaseUploadDetailsRepository releaseUploadDetailsRepository;

	@Autowired
	private ChangeLogBaseRepository changeLogBaseRepository;

	@Autowired
	private ChangeLogDetailsRepository changeLogDetailsRepository;

	@Autowired
	private TestCaseVersionsRepository testCaseVersionsRepository;

	@Autowired
	private SupportingDocumentsRepository supportingDocumentsRepository;

	@Autowired
	private TestCaseDetailsVersionHistoryRepository testCaseDetailsVersionHistoryRepository;

	@Autowired
	private DeliverableSchedulesDetailsRepository deliverableSchedulesDetailsRepository;

	@Autowired
	private DeliverableBillingDetailsRepository deliverableBillingDetailsRepository;

	@Autowired
	private CmmiProjectResourcesRepository cmmiProjectResourcesRepository;
	private ResourceRequestRepository resourceRequestRepository;

	@Autowired
	private PmpTrainingNeedsRepository pmpTrainingNeedsRepository;

	@Autowired
	private PmpProjectEscalationsRepository pmpProjectEscalationsRepository;

	@Autowired
	private PmpDevelopmentInfrastructureRepository pmpDevelopmentInfrastructureRepository;

	@Autowired
	private PmpMeasurementPlanRepository pmpMeasurementPlanRepository;

	@Autowired
	private PmpReusablesRepository pmpReusablesRepository;

	@Autowired
	private PMPResourceAllocationRepository pMPResourceAllocationRepository;

	@Autowired
	private PMPEffortQuantificationRepository pmpEffortQuantificationRepository;

	@Autowired
	private PointsofContactRepository pointsofContactRepository;

	@Autowired
	private TechdetailsRepository techdetailsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupsRepository groupsRepository;

	@Autowired
	private SubModuleSetupRepository subModuleSetupRepository;

	@Autowired
	private UserSetupRepository userSetupRepository;

	@Autowired
	private CisQueriesRepository cisQueriesRepository;

	@Autowired
	private PathTraversalRepository pathTraversalRepository;

	private final Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getName().equals("handler") || f.getName().equals("hibernateLazyInitializer")
					|| f.getName().equals("_$tls$");
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}).registerTypeAdapter(java.time.LocalDate.class,
			(JsonSerializer<java.time.LocalDate>) (src, typeOfSrc,
					context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
			.registerTypeAdapter(java.time.LocalDate.class,
					(JsonDeserializer<java.time.LocalDate>) (json, typeOfT, context) -> java.time.LocalDate
							.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
			.registerTypeAdapter(java.time.LocalTime.class,
					(JsonSerializer<java.time.LocalTime>) (src, typeOfSrc,
							context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME)))
			.registerTypeAdapter(java.time.LocalTime.class,
					(JsonDeserializer<java.time.LocalTime>) (json, typeOfT, context) -> java.time.LocalTime
							.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME))
			.registerTypeAdapter(java.time.LocalDateTime.class,
					(JsonSerializer<java.time.LocalDateTime>) (src, typeOfSrc,
							context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
			.registerTypeAdapter(java.time.LocalDateTime.class,
					(JsonDeserializer<java.time.LocalDateTime>) (json, typeOfT, context) -> java.time.LocalDateTime
							.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
			.create();

	public Collection<ProjectDetailsVo> getAllProjectNames() {
		Collection<ProjectDetails> projectDetails = projectDetailsRepository
				.findAllByActiveStatusAndBillableStatusOrderByProjectIdDesc("Y", "Y");
		Collection<ProjectDetailsVo> projectDetailsVos = new ArrayList<>();
		for (ProjectDetails projectDetail : projectDetails) {
			projectDetailsVos.add(gson.fromJson(gson.toJson(projectDetail), ProjectDetailsVo.class));
		}
		return projectDetailsVos;
	}

	public List<PathTraversal> getPathTraversalsByProjectId(Long projectId) {
		return pathTraversalRepository.findByProjectIdAndDeleteStatusOrderByTraversalDateDesc(projectId, "N");
	}

	public Collection<ResourceAllocationDetails> getAllResourceAllocationDetailsList(Integer projectId) {
		try {
			return resourceAllocationDetailsRepository.findByProjectId(projectId);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public Collection<ProjectDetailsVo> getProjectDetailsByClientId(Long clientId) {
		Collection<ProjectDetails> projectDetails = projectDetailsRepository.findAllByClientRegisterId(clientId);
		Collection<ProjectDetailsVo> projectDetailsVos = new ArrayList<>();
		for (ProjectDetails projectDetail : projectDetails) {
			projectDetailsVos.add(gson.fromJson(gson.toJson(projectDetail), ProjectDetailsVo.class));
		}
		return projectDetailsVos;
	}

	public Collection<PmpGdReleasedVo> getReleasedGdsByProjectId(Integer projectId) {
		Collection<PmpGdReleased> pmpGdReleaseds = pmpGdReleasedRepository
				.findByTypeAndProjectIdOrderByGdReleasedIdDesc("GD", projectId);
		Collection<PmpGdReleasedVo> pmpGdReleasedVos = new ArrayList<>();
		for (PmpGdReleased pmpGdReleased : pmpGdReleaseds) {
			pmpGdReleasedVos.add(gson.fromJson(gson.toJson(pmpGdReleased), PmpGdReleasedVo.class));
		}
		return pmpGdReleasedVos;
	}

	public CmmiKickOffMeetingVo getKickOffMeetingsByProjectId(Long projectId) {
		Long cmmiProjectId = cmmiProjectRepository.findIdByProjectId(projectId);
		CmmiKickOffMeeting meeting = cmmiKickOffMeetingRepository.findByCmmiProjectId(cmmiProjectId);
		return gson.fromJson(gson.toJson(meeting), CmmiKickOffMeetingVo.class);
	}

	public Collection<CmpUploadDetailsVo> getCmpDetailsByProjectId(Integer projectId) {
		Collection<CmpUploadDetails> cmpUploadDetails = cmpUploadDetailsRepository
				.findAllByCmpDetailsProjectIdAndCmpDetailsDeleteStatusOrderByCmpDetailsCmpIdDesc(projectId, "N");
		Collection<CmpUploadDetailsVo> cmpUploadDetailsVos = new ArrayList<>();
		for (CmpUploadDetails cmpUploadDetail : cmpUploadDetails) {
			cmpUploadDetailsVos.add(gson.fromJson(gson.toJson(cmpUploadDetail), CmpUploadDetailsVo.class));
		}
		return cmpUploadDetailsVos;
	}

	public Collection<TestPlanUploadsVo> getTestPlanUploads(Integer projectId) {
		Collection<TestPlanUploads> testPlanUploads = testPlanUploadsRepository
				.findAllByProjectDetailsProjectIdAndDeleteStatus(projectId, "N");
		Collection<TestPlanUploadsVo> testPlanUploadsVos = new ArrayList<>();
		for (TestPlanUploads testPlanUpload : testPlanUploads) {
			testPlanUploadsVos.add(gson.fromJson(gson.toJson(testPlanUpload), TestPlanUploadsVo.class));
		}
		return testPlanUploadsVos;
	}

	public Collection<QualityPlanVo> getQualityPlansByProjectId(Long projectId) {
		Long cmmiProjectId = cmmiProjectRepository.findIdByProjectId(projectId);
		Collection<QualityPlan> qualityPlans = qualityPlanRepository
				.findAllByCmmiProjectIdAndDeleteStatus(cmmiProjectId, "N");
		Collection<QualityPlanVo> qualityPlanVos = new ArrayList<>();
		for (QualityPlan qualityPlan : qualityPlans) {
			qualityPlanVos.add(gson.fromJson(gson.toJson(qualityPlan), QualityPlanVo.class));
		}
		return qualityPlanVos;
	}

	public Collection<ImpUploadDetailsVo> getImpDetailsByProjectId(Integer projectId) {
		Collection<ImpUploadDetails> impUploadDetails = impUploadDetailsRepository
				.findAllByImpProjectProjectIdAndImpImpStatusOrderByImpImpIdDesc(projectId, "Y");
		Collection<ImpUploadDetailsVo> impUploadDetailsVos = new ArrayList<>();
		for (ImpUploadDetails impUploadDetail : impUploadDetails) {
			impUploadDetailsVos.add(gson.fromJson(gson.toJson(impUploadDetail), ImpUploadDetailsVo.class));
		}
		return impUploadDetailsVos;
	}

	public Collection<DeliverySchedule> getDeliverableSchedulesByProjectId(Integer projectId) {
		Collection<DeliverableSchedules> deliverableSchedules = deliverableSchedulesRepository
				.findAllByProjectProjectIdAndDeleteStatus(projectId, "N");
		Collection<DeliverySchedule> deliverySchedules = new ArrayList<>();
		for (DeliverableSchedules ds : deliverableSchedules) {
			DeliverySchedule v = new DeliverySchedule();
			List<DeliverableSchedulesDetails> details = deliverableSchedulesDetailsRepository
					.findByDeliverableSchedulesDeffortId(ds.getDeffortId());
			DeliverableBillingDetails billingDetails = new DeliverableBillingDetails();
			if (!details.isEmpty()) {
				billingDetails = deliverableBillingDetailsRepository
						.findByDeliverableScheduleDetailsId(details.get(0).getId());
			}
			v.setId(ds.getDeffortId());
			v.setDeliverableItem(ds.getDeliverableItem());
			v.setActivity(ds.getActivity());
			v.setDeliveredRemarks(ds.getDeliveredRemarks());
			v.setDeleteStatus(ds.getDeleteStatus());
			v.setDeliverableFrequency(ds.getDeliverableFrequency().getFrequencyType());
			v.setDeliverableType(ds.getDeliverableType().getDeliverableType());

			if (!details.isEmpty()) {
				if (details.get(0).getDeliveryDate() != null) {
					v.setSubmitDateAsPerContract(details.get(0).getDeliveryDate().toLocalDate());
				}
				if (details.get(0).getDeliveredDate() != null) {
					v.setActualSubmitDate(details.get(0).getDeliveredDate().toLocalDate());
				}
			}

			if (billingDetails != null) {
				if (billingDetails.getPlannedBillDate() != null) {
					v.setApprovalDateAsPerContract((billingDetails.getPlannedBillDate().toLocalDate()));
				}
				if (billingDetails.getActualBillDate() != null) {
					v.setActualApprovalDate(billingDetails.getActualBillDate().toLocalDate());
				}
			}

			// if (ds.getProject() != null) {
			// v.setProject(gson.fromJson(gson.toJson(ds.getProject()),
			// com.cis.api.vo.ProjectDetailsVo.class));
			// }

			// Map other fields as they become available or needed
			deliverySchedules.add(v);
		}
		return deliverySchedules;
	}

	public Page<ReleaseUploadDetailsVo> getReleaseUploadDetailsByProjectIdPaged(Long projectId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ReleaseUploadDetails> releaseUploadDetailsPage = releaseUploadDetailsRepository
				.findAllByReleaseProjectProjectIdOrderByReleaseSubmissionDateDesc(projectId, pageable);

		List<ReleaseUploadDetailsVo> releaseUploadDetailsVos = releaseUploadDetailsPage.getContent().stream()
				.map(releaseDetail -> gson.fromJson(gson.toJson(releaseDetail), ReleaseUploadDetailsVo.class))
				.collect(Collectors.toList());

		return new PageImpl<>(releaseUploadDetailsVos, pageable, releaseUploadDetailsPage.getTotalElements());
	}

	// public Collection<ChangeLogBaseVo> getChangeLogByProjectId(Long projectId) {
	// Collection<ChangeLogBase> changeLogs = changeLogBaseRepository
	// .findByAllProjectProjectIdOrderByRequestDateDesc(projectId);
	// Collection<ChangeLogBaseVo> changeLogVos = new ArrayList<>();
	// for (ChangeLogBase changeLog : changeLogs) {
	// changeLogVos.add(gson.fromJson(gson.toJson(changeLog),
	// ChangeLogBaseVo.class));
	// }
	// return changeLogVos;
	// }

	public Page<ChangeLogBaseVo> getChangeLogByProjectIdPaged(Long projectId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("requestDate").descending());
		Page<ChangeLogBase> changeLogPage = changeLogBaseRepository.findAllByProjectProjectId(projectId, pageable);

		List<ChangeLogBaseVo> changeLogVos = changeLogPage.getContent().stream()
				.map(changeLog -> gson.fromJson(gson.toJson(changeLog), ChangeLogBaseVo.class))
				.collect(Collectors.toList());

		return new PageImpl<>(changeLogVos, pageable, changeLogPage.getTotalElements());
	}

	public Collection<ChangeLogDetailsVo> getChangeLogDetailsByBaseId(Long baseId) {
		Collection<ChangeLogDetails> changeLogDetails = changeLogDetailsRepository
				.findAllByBaseVerIdOrderByDetIdDesc(baseId);
		Collection<ChangeLogDetailsVo> changeLogDetailsVos = new ArrayList<>();
		for (ChangeLogDetails changeLogDetail : changeLogDetails) {
			changeLogDetailsVos.add(gson.fromJson(gson.toJson(changeLogDetail), ChangeLogDetailsVo.class));
		}
		return changeLogDetailsVos;
	}

	public Page<ChangeLogDetailsVo> getChangeLogDetailsByBaseIdPaged(Long baseId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("detId").descending());
		Page<ChangeLogDetails> changeLogDetailsPage = changeLogDetailsRepository.findAllByBaseVerId(baseId, pageable);

		List<ChangeLogDetailsVo> changeLogDetailsVos = changeLogDetailsPage.getContent().stream()
				.map(changeLogDetail -> gson.fromJson(gson.toJson(changeLogDetail), ChangeLogDetailsVo.class))
				.collect(Collectors.toList());

		return new PageImpl<>(changeLogDetailsVos, pageable, changeLogDetailsPage.getTotalElements());
	}

	public Collection<CrsVersionVo> getCrsVersionsByProjectId(Integer projectId) {
		Collection<CrsVersion> crsVersions = crsVersionRepository.findByProjectIdOrderByCrsVersionIdDesc(projectId);
		Collection<CrsVersionVo> crsVersionVos = new ArrayList<>();
		for (CrsVersion crsVersion : crsVersions) {
			crsVersionVos.add(gson.fromJson(gson.toJson(crsVersion), CrsVersionVo.class));
		}
		return crsVersionVos;
	}

	public Collection<CrsVerHistory> getCrsVersionshistoryByCrsVerId(Integer crsVerId) {
		return crsVerHistoryRepository.findByCrsVerCrsVersionIdOrderByCrsVerHisIdAsc(crsVerId);
	}

	// private CrsVerHistoryVo toVo(CrsVerHistory h) {
	//
	// Crs c = h.getCrs();
	//
	// String crsPoint = (h.getCrsPoint() != null && !h.getCrsPoint().isEmpty()) ?
	// h.getCrsPoint() : c.getCrsDetails();
	//
	// return
	// CrsVerHistoryVo.builder().crsVerHisId(h.getCrsVerHisId()).crsVersion(h.getCrsVer().getCrsVersionId())
	// .crsId(c.getCrsId())
	//
	// .status(h.getStatus()).date(h.getDate() != null ? h.getDate().toString() :
	// null).action(h.getAction())
	// .doneBy(h.getDoneBy())
	//
	// .crsRefNo(h.getCrsRefNo() != null ? h.getCrsRefNo() :
	// c.getCrsNo()).crsNo(c.getCrsNo())
	// .crsPoint(crsPoint).moduleName(c.getModule().getModuleName()).type(c.getTypes()).build();
	// }

	private CrsVersionVo toCrsVersionVo(CrsVersion v) {
		if (v == null)
			return null;

		CrsVersionVo vo = new CrsVersionVo();
		vo.setCrsVersionId(v.getCrsVersionId());
		vo.setProjectId(v.getProjectId());
		vo.setGroupId(v.getGroupId());
		vo.setCrsVersion(v.getCrsVersion());
		vo.setCrsApprovalStatus(v.getCrsApprovalStatus());
		vo.setCopyFromVerId(v.getCopyFromVerId());
		vo.setTorVerId(v.getTorVerId());
		vo.setCrsVerSubmitStatus(v.getCrsVerSubmitStatus());
		vo.setCsrReviseStatus(v.getCsrReviseStatus());
		vo.setCrsCreatedOn(v.getCrsCreatedOn());
		vo.setCrsCreatedBy(v.getCrsCreatedBy());
		vo.setApprovedUser(v.getApprovedUser());
		vo.setSubmittedUser(v.getSubmittedUser());
		vo.setReleasedUser(v.getReleasedUser());
		vo.setSubmittedDate(v.getSubmittedDate());
		vo.setApprovedDate(v.getApprovedDate());
		vo.setReleasedDate(v.getReleasedDate());
		vo.setCreatedUser(v.getCreatedUser());
		vo.setReleasedStatus(v.getReleasedStatus());
		vo.setPmpid(v.getPmpid());

		return vo;
	}

	public Collection<SrsVersionVo> getSrsVersionsByProjectId(Integer projectId) {
		Collection<SrsVersion> srsVersions = srsVersionRepository.findAllByProjectId(projectId);
		Collection<SrsVersionVo> srsVersionVos = new ArrayList<>();
		for (SrsVersion srsVersion : srsVersions) {
			srsVersionVos.add(gson.fromJson(gson.toJson(srsVersion), SrsVersionVo.class));
		}
		return srsVersionVos;
	}

	public List<SrsVersionHistoryVo> getSrsDetailListBySrsverid(Integer srsverid) {
		int count = srsVersionHistoryRepository.countBySrsVersionSrsVersionId(srsverid);
		System.out.println("Number of SRS Details: " + count);

		// Fetch list as VO
		return srsVersionHistoryRepository.findSrsVersionHistoryVosBySrsVersionId(srsverid);
	}

	public Collection<LevelOneEstimationVersionVo> getLevelOneEstimationsByProjectId(Integer projectId) {
		Collection<LevelOneEstimationVersion> levelOneEstimationVersions = levelOneEstimationVersionRepository
				.findAllByProjectIdOrderByLevelOneEstimationVersionIdDesc(projectId);
		Collection<LevelOneEstimationVersionVo> levelOneEstimationVersionVos = new ArrayList<>();
		for (LevelOneEstimationVersion estimationVersion : levelOneEstimationVersions) {
			levelOneEstimationVersionVos
					.add(gson.fromJson(gson.toJson(estimationVersion), LevelOneEstimationVersionVo.class));
		}
		return levelOneEstimationVersionVos;
	}

	public Collection<DdVo> getDdDetailsByProjectId(Integer projectId) {
		Collection<Dd> dds = ddRepository.findAllByProjectIdOrderByDdDateDiscussionDesc(projectId);
		Collection<DdVo> ddVos = new ArrayList<>();
		for (Dd dd : dds) {
			DdVo ddVo = gson.fromJson(gson.toJson(dd), DdVo.class);
			ddVos.add(ddVo);
		}
		return ddVos;
	}

	public Collection<DddDetailsVo> getDddDetailsByProjectId(Long projectId) {
		Collection<DddDetails> dddDetails = dddDetailsRepository.findAllByProjectIdOrderBySubmissionDateDesc(projectId);
		Collection<DddDetailsVo> dddDetailsVos = new ArrayList<>();
		for (DddDetails dddDetail : dddDetails) {
			DddUploadDetails sddUploads = dddUploadDetailsRepository.findByDddDddId(dddDetail.getDddId());
			dddDetail.setFileName(sddUploads.getFileName());
			dddDetailsVos.add(gson.fromJson(gson.toJson(dddDetail), DddDetailsVo.class));
		}
		return dddDetailsVos;
	}

	public Collection<TestCaseVersionsVo> getTestCaseVersionsByProjectId(Long projectId) {
		Collection<TestCaseVersions> testCaseVersions = testCaseVersionsRepository
				.findAllByProjectIdOrderByVersionIdDesc(projectId);
		Collection<TestCaseVersionsVo> testCaseVersionsVos = new ArrayList<>();
		for (TestCaseVersions testCaseVersion : testCaseVersions) {
			testCaseVersionsVos.add(gson.fromJson(gson.toJson(testCaseVersion), TestCaseVersionsVo.class));
		}
		return testCaseVersionsVos;
	}

	public TestingCountVo getTestingCountsByProjectId(Long projectId) {
		TestingCountVo counts = new TestingCountVo();

		counts.setTotalSrs(getCount("SELECT count(*) FROM pts.srs_version where project_Id = :projectId", projectId));
		counts.setTotalUnitTestDefects(
				getCount("SELECT count(*) FROM pts.test_report_result where project_Id = :projectId", projectId));

		// Complex query for total test cases (adapted from user's provided HQL)
		String testCaseSql = "SELECT count(distinct tcdvh.test_Case_Id) " + "FROM pts.test_case_ver_history AS tcdvh "
				+ "WHERE tcdvh.version_Id IN (" + "SELECT tcdv.version_Id FROM pts.test_case_versions AS tcdv "
				+ "WHERE tcdv.project_Id = :projectId) " + "AND tcdvh.status IS NULL";

		try {
			Query query = entityManager.createNativeQuery(testCaseSql);
			query.setParameter("projectId", projectId);
			Object result = query.getSingleResult();
			counts.setTotalTestCases(((Number) result).longValue());
		} catch (Exception e) {
			counts.setTotalTestCases(0L);
		}

		counts.setFailedTestCases(getCount(
				"SELECT count(*) FROM pts.testreport_history where project_Id = :projectId and passFailStatus = 'F'",
				projectId));
		counts.setTestingDefects(getCount(
				"SELECT count(*) from pts.test_report_result as abc where abc.project_Id=:projectId and abc.types!=\"Unit\"",
				projectId));
		counts.setOpenBugs(getCount(
				"SELECT count(*) from pts.test_report_result as trs where trs.taskStatus='Closed' and trs.project_Id=projectId and trs.types!=\"Unit\"",
				projectId));
		counts.setClosedBugs(getCount(
				"SELECT count(*) from pts.test_report_result as trs where trs.taskStatus='Closed' and trs.project_Id=:projectId "
						+ "and a.types!=:Unit",
				projectId));
		counts.setProductionBugs(getCount(
				"SELECT count(*) FROM pts.client_request as cr where cr.project_Id=:projectId and cr.request_Type like 'Bug'",
				projectId));
		// counts.setDeferredBugs(getCount(
		// "SELECT count(*) FROM pts.testing_defects where project_Id = :projectId and
		// bug_Status = 'Deferred'",
		// projectId));

		return counts;
	}

	public Collection<SupportingDocumentsVo> getSupportingDocumentsByCmmiProjectId(Long projectId) {
		Long cmmiProjectId = cmmiProjectRepository.findIdByProjectId(projectId);
		Collection<SupportingDocuments> supportingDocuments = supportingDocumentsRepository
				.findAllByCmmiProjectIdAndDeleteStatusOrderBySubmissionDateDesc(cmmiProjectId, "N");
		Collection<SupportingDocumentsVo> supportingDocumentsVos = new ArrayList<>();
		for (SupportingDocuments suppDocument : supportingDocuments) {
			supportingDocumentsVos.add(gson.fromJson(gson.toJson(suppDocument), SupportingDocumentsVo.class));
		}
		return supportingDocumentsVos;
	}

	public Page<SrsVersionHistoryVo> getUnassignedPoint(Long projectId, String assignStatus, Long moduleId,
			int pageNumber) {

		// 1. Get Project
		// Project project = getCmmiProject(cmmiProjectId);
		// if (project == null) {
		// throw new RuntimeException("Project not found");
		// }

		// 2. Get latest SRS version
		SrsVersion latestVersion = srsVersionRepository.getLatestSrsVersion(projectId);
		if (latestVersion == null) {
			throw new RuntimeException("No SRS Version found");
		}

		Integer srsVersionId = latestVersion.getSrsVersionId();

		// 3. Pagination setup (10 rows)
		Pageable pageable = PageRequest.of(pageNumber, 10);

		// 4. If/Else for UA or A
		if ("UA".equals(assignStatus)) {
			return srsVersionHistoryRepository.getUnassignedPointsVo(srsVersionId, moduleId, pageable);
		} else if ("A".equals(assignStatus)) {
			return srsVersionHistoryRepository.getAssignedPointsVo(srsVersionId, moduleId, pageable);
		} else {
			throw new UnsupportedOperationException("Invalid assignStatus: " + assignStatus);
		}
	}

	// private CmmiProject getCmmiProject(long cmmiProjectId) {
	// // TODO Auto-generated method stub
	// return cmmiProjectRepository.findProject(cmmiProjectId);
	// }

	// public UnitTestListResponseDto getUnitTestList(Integer projectId, Integer
	// groupId, int pageNo) {
	//
	// Pageable pageable = PageRequest.of(pageNo - 1, 15,
	// Sort.by("id").descending());
	//
	// Page<TestReportResult> page =
	// testReportResultRepository.findByProjectIdAndTypesContainingIgnoreCase(
	// projectId, "Unit", pageable);
	//
	// List<SeatUserAlloted> users =
	// seatUserAllotedRepository.findUsersAssignedForUnitTests(projectId);
	//
	// List<ModuleSetup> modules =
	// moduleSetupRepository.findByProjectProjectIdAndDeleteStatus(projectId, "N");
	//
	// List<BugType> bugTypes =
	// bugTypeRepository.findByDeleteStatus("N");
	//
	// return new UnitTestListResponseDto(
	// users,
	// modules,
	// bugTypes,
	// page.getContent(),
	// page.getNumber() + 1,
	// page.getTotalPages(),
	// page.getTotalElements()
	// );
	// }
//<<<<<<< HEAD
//	
//	 public Page<RtmRowDto> rtmDetailsByPidAndGid(
//		        Long pId,
//		        Integer gId,
//		        String status,
//=======
//>>>>>>> branch 'Development' of https://git.kran.co.in/product/galaxy/cis/cis_api.git

	public Page<RtmRowDto> rtmDetailsByPidAndGid(
			Long pId,
			Integer gId,
			String status,

			Integer page,
			Integer pageSize) {

		// Pageable (page is already springPage from controller)
		Pageable pageable = PageRequest.of(page, pageSize);

		List<RtmRowDto> results = new ArrayList<>();

		// 1️⃣ Get Modules
		List<ModuleSetup> modules = moduleSetupRepository.getModules(pId, gId);

		for (ModuleSetup module : modules) {

			// 2️⃣ CRS per module
			List<Crs> crsList = crsRepository.findByModuleId(module.getModuleId());

			for (Crs crs : crsList) {

				// 3️⃣ SRS per CRS
				List<Srs> srsList = srsRepository.findByCrsId(crs.getCrsId());

				for (Srs srs : srsList) {

					// 4️⃣ SDD
					SDDEntries sdd = null;
					if (srs.getSddEntryId() != null) {
						sdd = sDDEntriesRepository.getEntry(srs.getSddEntryId());
					}

					// 5️⃣ Test Cases
					List<TestCaseDetails> tcList = testCaseDetailsRepository.getTestCases(srs.getSrsId());

					if (tcList == null || tcList.isEmpty()) {
						results.add(buildRow(module, crs, srs, sdd, null));
					} else {
						for (TestCaseDetails tc : tcList) {
							results.add(buildRow(module, crs, srs, sdd, tc));
						}
					}
				}
			}
		}

		// 6️⃣ Manual Pagination **SAFE**
		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageSize, results.size());

		// If page > total pages => return empty
		if (start >= results.size()) {
			return new PageImpl<>(Collections.emptyList(), pageable, results.size());
		}

		List<RtmRowDto> paged = results.subList(start, end);

		return new PageImpl<>(paged, pageable, results.size());
	}

	private RtmRowDto buildRow(
			ModuleSetup module,
			Crs crs,
			Srs srs,
			SDDEntries sdd,
			TestCaseDetails tc) {
		return new RtmRowDto(
				module.getModuleName(),
				crs.getCrsNo(),
				crs.getCrsDetails(),
				crs.getDependentRequirement(),

				srs.getSrsNo(),
				srs.getSrsDetails(),
				srs.getSrsRefId() != null ? srs.getSrsRefId().toString() : null,

				sdd != null ? sdd.getActivity() : null,
				sdd != null ? sdd.getEntryNo() : null,

				tc != null ? tc.getTestCaseNo() : null,
				tc != null ? tc.getTestCaseDetails() : null);
	}

	public Page<UnitTestResultDto> getUnitTestList(Integer projectId, int page, int size) {

		int pageSize = 15; // max entries per page

		Pageable pageable = PageRequest.of(page, size);

		Page<TestReportResult> pageResult = testReportResultRepository.findAllUnitTests(projectId, pageable);

		return pageResult.map(tr -> {

			UnitTestResultDto dto = new UnitTestResultDto();

			dto.setId(tr.getId());
			dto.setBugId(tr.getBugId());
			dto.setBugDesc(tr.getBugDesc());
			dto.setStepsToReproduce(tr.getStepsToReproduce());
			dto.setSeverity(tr.getSeverity());
			dto.setPriority(tr.getPriority());

			dto.setModuleName(tr.getModule() != null ? tr.getModule().getModuleName() : null);

			dto.setTypes(tr.getTypes());
			dto.setStatus(tr.getStatus());

			dto.setBugType(tr.getBugType() != null ? tr.getBugType().getBugType() : null);

			dto.setFileName(tr.getTestReportUpload() != null ? tr.getTestReportUpload().getFileName() : null);

			dto.setUpdatedOn(tr.getUpdatedOn());
			dto.setTaskStatus(tr.getTaskStatus());

			return dto;
		});
	}

	private Long getCount(String sql, Long projectId) {
		try {
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("projectId", projectId);
			Object result = query.getSingleResult();
			return ((Number) result).longValue();
		} catch (Exception e) {
			return 0L;
		}
	}

	@Autowired
	private NewAttachmentsRepository newAttachmentsRepository;

	public Page<NewAttachments> getPerfomanceTestingDetailsByCmmiProjectId(Long cmmiProjectId, int page, int size) {

		int pageSize = 15; // max rows per page

		Pageable pageable = PageRequest.of(page, size); // pageNo starts from 1

		String code = "PT"; // <-- use your actual code for performance testing
		String deleteStatus = "N";

		return newAttachmentsRepository.findByCmmiProjectIdAndAttachmentTypeAndDeleteStatusOrderBySubmissionDateDesc(
				cmmiProjectId, code, deleteStatus, pageable);
	}

	public Page<TestReportResult> getBugReportDetails(Integer pid, int page, int size) {

		Pageable pageable = PageRequest.of(page, size); // pageNo starts from 1

		return testReportResultRepository.findBugReportPage(pid, pageable);
	}

	public Page<TestReportResult> filterUnitTestBugs(Integer projectId, Integer moduleId, Integer userId,
			String taskStatus, Integer bugTypeId, String fromDate, String toDate, Integer pageNo) {

		List<Integer> testResultIds = null;

		if (userId != null && userId != 0) {
			testResultIds = testReportTaskDetailsRepository.findTestResultIdsByProjectAndUser(projectId, userId);
		}
		LocalDate fDate = (fromDate != null && !fromDate.isBlank()) ? LocalDate.parse(fromDate) : null;
		LocalDate tDate = (toDate != null && !toDate.isBlank()) ? LocalDate.parse(toDate) : null;

		Pageable pageable = PageRequest.of(pageNo, 15); // <-- MAX 15 ENTRIES

		return testReportResultRepository.filterUnitTestResults(projectId,
				(moduleId != null && moduleId != 0) ? moduleId : null,
				(taskStatus != null && !taskStatus.equals("0")) ? taskStatus : null,
				(bugTypeId != null && bugTypeId != 0) ? bugTypeId : null, fDate, tDate,
				(testResultIds != null && !testResultIds.isEmpty()) ? testResultIds : null, pageable);
	}

	public Page<TestReportResultDto> filterTestReportBugs(Integer projectId, Integer moduleId, Long userId,
			String taskStatus, Integer versionId, String types, Integer bugTypeId, Integer pageNo, Integer pageSize) {

		// Step 1: Fetch testResultIds only if user filter applied
		List<Integer> testResultIds = null;

		if (userId != null && userId != 0) {
			testResultIds = testReportTaskDetailsRepository.findTestResultIds(projectId, userId);
		}

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		// Step 2: Call repository
		Page<TestReportResult> pageData = testReportResultRepository.filterTestReportBugs(projectId,
				(moduleId != null && moduleId != 0) ? moduleId : null,
				(taskStatus != null && !taskStatus.equals("0")) ? taskStatus : null,
				(versionId != null && versionId != 0) ? versionId : null,
				(types != null && !types.equals("0")) ? types : null,
				(bugTypeId != null && bugTypeId != 0) ? bugTypeId : null,
				(testResultIds != null && !testResultIds.isEmpty()) ? testResultIds : null, pageable);

		// Step 3: Convert Entity → DTO
		List<TestReportResultDto> dtoList = pageData.getContent()

				.stream().map(tr -> new TestReportResultDto(tr, getTestCaseNo(tr.getTestReportId()) // your method
				)).collect(Collectors.toList());

		// Step 4: Return paginated DTO
		return new PageImpl<>(dtoList, pageable, pageData.getTotalElements());
	}

	private TestCaseDetailsDTO convertToDTO(TestCaseDetails t) {
		TestCaseDetailsDTO dto = new TestCaseDetailsDTO();

		dto.setTestCaseId(t.getTestCaseId());
		dto.setTestCaseNo(t.getTestCaseNo());

		// Requirement → srs.srsNo
		if (t.getSrs() != null) {
			dto.setRequirement(t.getSrs().getSrsNo());
		} else {
			dto.setRequirement("General");
		}

		// Test Case Point → description + inputProcedure
		StringBuilder testCasePoint = new StringBuilder();
		if (t.getDescription() != null) {
			testCasePoint.append(t.getDescription());
		}
		if (t.getInputProcedure() != null && !t.getInputProcedure().isEmpty()) {
			if (testCasePoint.length() > 0)
				testCasePoint.append("\n");
			testCasePoint.append(t.getInputProcedure());
		}
		dto.setTestCasePoint(testCasePoint.toString());

		// Expected Result → testCaseDetails
		dto.setExpectedResult(t.getTestCaseDetails());

		// Type → map code to readable
		String type = "--";
		if (t.getTestCaseType() != null) {
			switch (t.getTestCaseType()) {
				case "F":
					type = "Functional";
					break;
				case "G":
					type = "GUI";
					break;
				case "I":
					type = "Integration";
					break;
				case "N":
					type = "Non Functional";
					break;
				case "R":
					type = "Re Testing";
					break;
				case "S":
					type = "System Testing";
					break;
			}
		}
		dto.setType(type);

		// Module → srs.crs.module.moduleName
		if (t.getSrs() != null && t.getSrs().getCrs() != null && t.getSrs().getCrs().getModule() != null) {
			dto.setModule(t.getSrs().getCrs().getModule().getModuleName());
		} else {
			dto.setModule("--");
		}

		// Updated By → user.userName
		if (t.getUser() != null) {
			dto.setUpdatedBy(t.getUser().getUserName());
		} else {
			dto.setUpdatedBy("--");
		}

		// Updated On → submissionDate
		dto.setUpdatedOn(t.getSubmissionDate());

		// Optional fields
		dto.setPassFailStatus(t.getPassFailStatus());
		dto.setSeverityStatus(t.getSeverityStatus());
		dto.setTestCaseRemarks(t.getTestCaseRemarks());
		dto.setCompletedDate(t.getCompletedDate());

		return dto;
	}

	public Page<TestCaseDetailsDTO> getTestCaseDetailsByVersion(
			Integer projectId,
			Integer tstCaseVerId,
			Integer srsVerId, // can keep, not used
			Integer page,
			Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);

		// Fetch testCaseIds by version only
		List<Integer> testCaseIds = testCaseDetailsVersionHistoryRepository.findTestCaseIds(tstCaseVerId);

		if (testCaseIds.isEmpty()) {
			return Page.empty(pageable);
		}

		// Manual pagination
		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(), testCaseIds.size());
		List<Integer> paginatedIds = testCaseIds.subList(start, end);

		// Fetch details for each ID
		List<TestCaseDetailsDTO> dtoList = paginatedIds.stream()
				.map(id -> testCaseDetailsRepository.findByTestCaseId(id))
				.filter(Optional::isPresent)
				.map(opt -> convertToDTO(opt.get()))
				.collect(Collectors.toList());

		return new PageImpl<>(dtoList, pageable, testCaseIds.size());
	}

	// private TestCaseDetailsDTO convertToTestCaseDTO(TestCaseDetails t) {
	// TestCaseDetailsDTO dto = new TestCaseDetailsDTO();
	// dto.setTestCaseId(t.getTestCaseId());
	// dto.setTestCaseNo(t.getTestCaseNo());
	// dto.setTestCaseDetails(t.getTestCaseDetails());----> The method
	// setTestCaseDetails(String) is undefined for the type TestCaseDetailsDTO
	// dto.setSrsVersionId(t.getSrsVersionId());----->The method
	// setSrsVersionId(Integer) is undefined for the type TestCaseDetailsDTO
	// dto.setPassFailStatus(t.getPassFailStatus());
	// dto.setSeverityStatus(t.getSeverityStatus());
	// dto.setTestCaseRemarks(t.getTestCaseRemarks());
	// dto.setSubmissionDate(t.getSubmissionDate());----->The method
	// setSubmissionDate(LocalDate) is undefined for the type TestCaseDetailsDTO
	// dto.setCompletedDate(t.getCompletedDate());
	// return dto;
	// }

	public PmpDetailsVo getPmpDetails(
			Integer pmpId,
			String substatus,
			String approvestatus,
			Integer projectId) {

		// ---- Load data ----
		List<PmpAssumptions> pmpAssumptionsList = new ArrayList<>();
		if (pmpId != null) {
			pmpAssumptionsList = pmpAssumptionsRepository.findByPmpId(pmpId);
		}

		List<PmpDependencies> pmpDependenciesList = new ArrayList<>();
		if (pmpId != null) {
			pmpDependenciesList = pmpDependenciesRepository.findByPmpId(pmpId);
		}

		List<ResourceAllocationDetails> resourceList = new ArrayList<>();
		if (projectId != null) {
			resourceList = resourceAllocationDetailsRepository.findByProjectId(projectId);
		}

		List<ResourceAllocationDetails> resourceListActive = new ArrayList<>();
		if (projectId != null) {
			resourceListActive = resourceAllocationDetailsRepository.findActiveResourceByProjectId(projectId);
		}

		List<CmmiProjectResources> trainingList = new ArrayList<>();
		if (projectId != null) {
			trainingList = cmmiProjectResourcesRepository.findByProjectId(projectId);
		}

		List<PmpTrainingNeeds> pmpTrainingNeedIdentifiersList = new ArrayList<>();
		if (pmpId != null) {
			pmpTrainingNeedIdentifiersList = pmpTrainingNeedsRepository.findByPmpId(pmpId);
		}

		List<PmpProjectEscalations> projectEscalationsList = new ArrayList<>();
		if (pmpId != null) {
			projectEscalationsList = pmpProjectEscalationsRepository.findByPmpId(pmpId);
		}

		List<PmpDevelopmentInfrastructure> developmentInfrastructureList = new ArrayList<>();
		if (pmpId != null) {
			developmentInfrastructureList = pmpDevelopmentInfrastructureRepository.findByPmpId(pmpId);
		}

		List<PmpMeasurementPlan> measurementPlanList = new ArrayList<>();

		if (pmpId != null) {
			measurementPlanList = pmpMeasurementPlanRepository.findByPmpIdAndMetricsSetup(pmpId);
		}

		List<PmpReusables> pmpReusablesList = new ArrayList<>();

		if (pmpId != null) {
			pmpReusablesList = pmpReusablesRepository.findByPmpId(pmpId);
		}

		List<PMPResourceAllocation> allPMPresourceallocationlist = new ArrayList<>();

		if (pmpId != null) {
			allPMPresourceallocationlist = pMPResourceAllocationRepository.findByPmpId(pmpId);
		}

		List<PMPEffortQuantification> getallPMPeffortquantificationlist = new ArrayList<>();

		if (pmpId != null) {
			getallPMPeffortquantificationlist = pmpEffortQuantificationRepository.findByPmpId(pmpId);

		}

		List<SeatUserAlloted> allSeatList = new ArrayList<>();
		allSeatList = seatUserAllotedRepository.findAllActiveSeatUserAllotments();

		// List<PointsofContact> contactdetailskran = new ArrayList<>();
		//
		// if (pmpId != null) {
		// contactdetailskran =
		// pointsofContactRepository.findByPmpId(pmpId);
		//
		//
		// }
		//
		// List<PointsofContact> contactdetailsclient = new ArrayList<>();
		//
		// if (pmpId != null) {
		// contactdetailsclient =
		// pointsofContactRepository.findByPmpIdContactClient(pmpId);
		//
		//
		// }
		//
		//
		//
		// List<UserSetup> activeuserslist = new ArrayList<>();
		//
		// if (pmpId != null) {
		// activeuserslist =
		// userSetupRepository.findActiveUsers();
		// }
		//
		// List<ModuleSetup> moduleList = new ArrayList<>();
		//
		// if (projectId != null) {
		// activeuserslist =
		// moduleSetupRepository.findModuleForPmp(projectId);
		// }
		//
		// Integer minGroupId = null;
		//
		// if (projectId != null) {
		// minGroupId = groupsRepository.findMinGroupIdByProject(projectId);
		// }
		//
		// List<ModuleSetup> moduleListForProject = new ArrayList<>();
		//
		// if (projectId != null) {
		// moduleListForProject =
		// moduleSetupRepository.findModuleForProjectPmp(projectId, minGroupId);
		// }
		//
		// moduleListForProject = this.attachSubModules(moduleListForProject);

		// ---- Build VO ----
		PmpDetailsVo vo = new PmpDetailsVo();
		vo.setAssumptions(pmpAssumptionsList);
		vo.setDependencies(pmpDependenciesList);
		vo.setResources(resourceList);
		vo.setActiveresources(resourceList);
		vo.setTrainingList(trainingList);
		vo.setPmpTrainingNeedIdentifiersList(pmpTrainingNeedIdentifiersList);
		vo.setProjectEscalationsList(projectEscalationsList);
		vo.setDevelopmentInfrastructureList(developmentInfrastructureList);
		vo.setMeasurementPlanList(measurementPlanList);
		vo.setPmpReusablesList(pmpReusablesList);
		vo.setAllPMPresourceallocationlist(allPMPresourceallocationlist);
		vo.setGetallPMPeffortquantificationlist(getallPMPeffortquantificationlist);
		vo.setAllSeatList(allSeatList);
		// vo.setContactdetailskran(contactdetailskran);
		// vo.setContactdetailsclient(contactdetailsclient);
		// vo.setActiveuserslist(activeuserslist);
		// vo.setModuleList(moduleListForProject);
		return vo;
	}

	public List<ModuleSetup> attachSubModules(List<ModuleSetup> moduleListForProject) {

		for (ModuleSetup module : moduleListForProject) {

			List<SubModuleSetup> subModules = subModuleSetupRepository.findByModuleId(module.getModuleId());

			module.setSubModuleList(subModules);
		}

		return moduleListForProject;
	}

	public String getTestCaseNo(Integer testReportHistoryId) {
		return testReportHistoryRepository.findTestCaseNo(testReportHistoryId);
	}

	public Page<TaskAssignments> userEffortDetailsByUserAndDate(
			Integer projectId,
			Long userId,
			String dateStatus,
			Integer pageNo,
			Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		LocalDate today = LocalDate.now();
		Date startDate;
		Date endDate;

		// WEEK
		if ("W".equalsIgnoreCase(dateStatus)) {

			LocalDate monday = today.with(DayOfWeek.MONDAY);
			LocalDate saturday = today.with(DayOfWeek.SATURDAY);

			startDate = Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
			endDate = Date.from(saturday.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
		}

		// MONTH
		else if ("M".equalsIgnoreCase(dateStatus)) {

			LocalDate firstDay = today.withDayOfMonth(1);
			LocalDate lastDay = today.withDayOfMonth(today.lengthOfMonth());

			startDate = Date.from(firstDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
			endDate = Date.from(lastDay.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
		}

		else {
			throw new RuntimeException("Invalid dateStatus. Allowed values: W / M");
		}

		// --------------------------
		// USER ID OPTIONAL
		// --------------------------
		if (userId == null) {
			return taskAssignmentsRepository.findByProjectAndDateRange(
					projectId, startDate, endDate, pageable);
		}

		return taskAssignmentsRepository.findByProjectUserAndDateRange(
				projectId, userId, startDate, endDate, pageable);
	}

	public String saveQuery(CisQueriesVo cisQueriesVo, org.springframework.web.multipart.MultipartFile file) {
		CisQueries cisQueries = new CisQueries();
		cisQueries.setProjectId(cisQueriesVo.getProjectId());
		cisQueries.setModuleId(cisQueriesVo.getModuleId());
		cisQueries.setMessage(cisQueriesVo.getMessage());
		cisQueries.setTabCode(cisQueriesVo.getTabCode());
		cisQueries.setUserId(cisQueriesVo.getUserId());
		cisQueries.setStatus(cisQueriesVo.getStatus());
		cisQueries.setLinkId(cisQueriesVo.getLinkId());
		cisQueries.setUpdatedOn(new Date());
		cisQueries.setActiveStatus(cisQueriesVo.getActiveStatus() != null ? cisQueriesVo.getActiveStatus() : "Y");

		// File upload logic
		if (file != null && !file.isEmpty()) {
			try {
				String os = System.getProperty("os.name").toLowerCase();
				String baseDir = os.contains("win") ? "C:" : "";
				String uploadDir = baseDir + "/pts_uploads/cis_uploads/";

				java.io.File dir = new java.io.File(uploadDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				String originalFileName = file.getOriginalFilename();
				String fileName = System.currentTimeMillis() + "_" + originalFileName;
				java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir + fileName);

				java.nio.file.Files.copy(file.getInputStream(), filePath,
						java.nio.file.StandardCopyOption.REPLACE_EXISTING);
				cisQueries.setAttachmentPath(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return "Error uploading file: " + e.getMessage();
			}
		}

		cisQueriesRepository.save(cisQueries);
		return "Query saved successfully";
	}

	public List<CisQueriesVo> getQueries(Integer projectId, Integer moduleId, String tabCode, Integer linkId) {
		List<CisQueries> queries = cisQueriesRepository.findQueries(projectId, moduleId, tabCode, linkId);
		List<CisQueriesVo> queryVos = new ArrayList<>();
		for (CisQueries query : queries) {
			CisQueriesVo vo = gson.fromJson(gson.toJson(query), CisQueriesVo.class);
			if (vo.getAttachmentPath() != null && !vo.getAttachmentPath().isEmpty()) {
				vo.setAttachmentPath("https://galaxy.kran.co.in/pts_uploads/cis_uploads/" + vo.getAttachmentPath());
			}
			queryVos.add(vo);
		}
		return queryVos;
	}

	public org.springframework.http.ResponseEntity<org.springframework.core.io.Resource> downloadQueryAttachment(
			String fileName, jakarta.servlet.http.HttpServletRequest request) {
		try {
			String os = System.getProperty("os.name").toLowerCase();
			String baseDir = os.contains("win") ? "C:" : "";
			String uploadDir = baseDir + "/pts_uploads/cis_uploads/";

			java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir).resolve(fileName).normalize();
			org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(
					filePath.toUri());

			if (resource.exists()) {
				String contentType = null;
				try {
					contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				} catch (java.io.IOException ex) {
					// Fallback
				}

				if (contentType == null) {
					contentType = "application/octet-stream";
				}

				return org.springframework.http.ResponseEntity.ok()
						.contentType(org.springframework.http.MediaType.parseMediaType(contentType))
						.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
								"attachment; filename=\"" + resource.getFilename() + "\"")
						.body(resource);
			} else {
				return org.springframework.http.ResponseEntity.notFound().build();
			}
		} catch (java.net.MalformedURLException ex) {
			return org.springframework.http.ResponseEntity.notFound().build();
		} catch (Exception e) {
			return org.springframework.http.ResponseEntity.internalServerError().build();
		}
	}
	
	// ── NEW: monthly count for graph ──────────────────────────────────────────
	public List<MonthlyResourceCountVo> getMonthlyResourceCount(
	        Integer projectId, Integer year) {
	    try {
	        List<Object[]> rows = resourceAllocationDetailsRepository
	                .findMonthlyResourceCountByProjectId(projectId, year);

	        return rows.stream()
	                   .map(r -> new MonthlyResourceCountVo(
	                           ((Number) r[0]).intValue(),   // year
	                           ((Number) r[1]).intValue(),   // month
	                           ((Number) r[2]).longValue())) // count
	                   .collect(Collectors.toList());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}

	// ── NEW: detail list when user clicks a bar ───────────────────────────────
	public Collection<ResourceAllocationDetails> getResourcesByMonth(
	        Integer projectId, int year, int month) {
	    try {
	        return resourceAllocationDetailsRepository
	                .findByProjectIdAndMonth(projectId, year, month);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}
	

	public String saveProjectRolePrivileges(ProjectPrivilegeSaveRequestDto request) {
		if (request.getProjectId() == null || request.getRoleName() == null) {
			throw new IllegalArgumentException("Project ID and Role Name are required");
		}

		Long projectId = request.getProjectId();
		String roleName = request.getRoleName();

		for (ProjectPrivilegeSaveRequestDto.PrivilegeItemDto item : request.getPrivileges()) {
			Optional<CisProjectRolePrivileges> existingOpt;
			if (item.getSubtabName() == null || item.getSubtabName().isEmpty()) {
				existingOpt = cisProjectRolePrivilegesRepository
						.findByProjectIdAndRoleNameAndTabNameAndSubtabNameIsNull(
								projectId, roleName, item.getTabName());
			} else {
				existingOpt = cisProjectRolePrivilegesRepository.findByProjectIdAndRoleNameAndTabNameAndSubtabName(
						projectId, roleName, item.getTabName(), item.getSubtabName());
			}

			CisProjectRolePrivileges privilege = existingOpt.orElse(new CisProjectRolePrivileges());
			if (privilege.getId() == null) {
				privilege.setProjectId(projectId);
				privilege.setRoleName(roleName);
				privilege.setUserId(1L); // Default value as user_id is currently NOT NULL in the table
				privilege.setTabName(item.getTabName());
				privilege.setSubtabName(
						item.getSubtabName() != null && !item.getSubtabName().isEmpty() ? item.getSubtabName() : null);
				privilege.setCreatedBy("ADMIN"); // Can be replaced by real logged-in user in future
			} else {
				privilege.setUpdatedBy("ADMIN");
			}

			privilege.setCanRead(item.getCanRead() != null ? item.getCanRead() : false);
			privilege.setCanWrite(item.getCanWrite() != null ? item.getCanWrite() : false);
			privilege.setCanDelete(item.getCanDelete() != null ? item.getCanDelete() : false);
			privilege.setCanApprove(item.getCanApprove() != null ? item.getCanApprove() : false);
			privilege.setCanVerify(item.getCanVerify() != null ? item.getCanVerify() : false);

			cisProjectRolePrivilegesRepository.save(privilege);
		}

		return "Privileges saved successfully";
	}

	public java.util.List<CisProjectRolePrivileges> getProjectRolePrivileges(Long projectId) {
		return cisProjectRolePrivilegesRepository.findByProjectId(projectId);
	}
}
