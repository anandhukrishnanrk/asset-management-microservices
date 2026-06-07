package com.cis.api.vo;

import java.util.List;

import com.cis.api.entity.CmmiProjectResources;
import com.cis.api.entity.ModuleSetup;
import com.cis.api.entity.PMPEffortQuantification;
import com.cis.api.entity.PMPResourceAllocation;
import com.cis.api.entity.PmpAssumptions;
import com.cis.api.entity.PmpDependencies;
import com.cis.api.entity.PmpDevelopmentInfrastructure;
import com.cis.api.entity.PmpMeasurementPlan;
import com.cis.api.entity.PmpProjectEscalations;
import com.cis.api.entity.PmpReusables;
import com.cis.api.entity.PmpTrainingNeeds;
import com.cis.api.entity.PointsofContact;
import com.cis.api.entity.ResourceAllocationDetails;
import com.cis.api.entity.SeatUserAlloted;
import com.cis.api.entity.UserSetup;

import lombok.Data;

@Data
public class PmpDetailsVo {

    private List<PmpAssumptions> assumptions;
    private List<PmpDependencies> dependencies;
    private List<ResourceAllocationDetails> resources;
    private List<ResourceAllocationDetails> Activeresources;
    private List<CmmiProjectResources> trainingList;
    private List<PmpTrainingNeeds> pmpTrainingNeedIdentifiersList;
    private List<PmpProjectEscalations> projectEscalationsList;
    private List<PmpDevelopmentInfrastructure> developmentInfrastructureList;
    private List<PmpMeasurementPlan> measurementPlanList;
    private List<PmpReusables>	 pmpReusablesList;
private List<PMPResourceAllocation> allPMPresourceallocationlist;
private List<PMPEffortQuantification> getallPMPeffortquantificationlist;
private List<SeatUserAlloted> allSeatList;
//private List<PointsofContact> contactdetailskran;
//private List<PointsofContact> contactdetailsclient;
//private List<UserSetup> activeuserslist;
//private List<ModuleSetup> moduleList;
}
