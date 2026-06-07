package com.asset.asset_api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asset.asset_api.dto.AmcVo;
import com.asset.asset_api.dto.AssetStoreResponse;
import com.asset.asset_api.dto.ReturnAssetRequest;
import com.asset.asset_api.entity.AMCDetails;
import com.asset.asset_api.entity.AMCPeriod;
import com.asset.asset_api.entity.AMCVisit;
import com.asset.asset_api.entity.Asset;
import com.asset.asset_api.entity.AssetAllotment;
import com.asset.asset_api.entity.AssetDisposal;
import com.asset.asset_api.entity.AssetFlow;
import com.asset.asset_api.entity.AssetMaintenance;
import com.asset.asset_api.entity.AssetOffice;
import com.asset.asset_api.entity.AssetStore;
import com.asset.asset_api.entity.AssetTransfer;
import com.asset.asset_api.entity.HierarchySetup;
import com.asset.asset_api.entity.SeatUserAlloted;
import com.asset.asset_api.entity.SetupCategory;
import com.asset.asset_api.entity.SetupGroup;
import com.asset.asset_api.entity.SetupSubCategory;
import com.asset.asset_api.entity.UserSetup;
import com.asset.asset_api.repository.AMCPeriodRepository;
import com.asset.asset_api.repository.AMCVisitRepository;
import com.asset.asset_api.repository.AmcDetailsRepository;
import com.asset.asset_api.repository.AssetAllotmentRepository;
import com.asset.asset_api.repository.AssetDisposalRepository;
import com.asset.asset_api.repository.AssetFlowRepository;
import com.asset.asset_api.repository.AssetMaintenanceRepository;
import com.asset.asset_api.repository.AssetOfficeRepository;
import com.asset.asset_api.repository.AssetRepository;
import com.asset.asset_api.repository.AssetStoreRepository;
import com.asset.asset_api.repository.AssetTransferRepository;
import com.asset.asset_api.repository.CategoryRepository;
import com.asset.asset_api.repository.GroupRepository;
import com.asset.asset_api.repository.HierarchySetupRepository;
import com.asset.asset_api.repository.SeatUserAllotedRepository;
import com.asset.asset_api.repository.SubCategoryRepository;
import com.asset.asset_api.repository.SubcategeryRepository;
import com.asset.asset_api.repository.UserSetupRepository;
import com.asset.asset_api.vo.AllotListVo;
import com.asset.asset_api.vo.AssetAllotmentResponse;
import com.asset.asset_api.vo.AssetInformationVo;
import com.asset.asset_api.vo.MaintenanceViewResponse;

import jakarta.transaction.Transactional;

@Service
public class AssetService {
	
	@Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private AssetStoreRepository assetStoreRepository;

    @Autowired
    private AssetAllotmentRepository assetAllotmentRepository;
    
    @Autowired
    private HierarchySetupRepository hierarchySetupRepository;
    
    @Autowired
    private AssetFlowRepository assetFlowRepository;
    
    @Autowired
    private AmcDetailsRepository amcDetailsRepository;
    
    @Autowired
    private AssetTransferRepository assetTransferRepository;
    
    @Autowired
    private AssetMaintenanceRepository assetMaintenanceRepository;
    
    @Autowired
    private AssetDisposalRepository assetDisposalRepository;

    @Transactional
    public AssetStoreResponse getAssetStore(Integer groupId,
                                            Integer categoryId,
                                            Integer subCategoryId,
                                            int page,
                                            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<AssetStore> assetPage;
        List<SetupCategory> categoryList;
        List<SetupSubCategory> subCategoryList;
        List<SetupGroup> groupList = groupRepository.findAll();

        if (groupId != null && categoryId == 0 && subCategoryId == 0) {

            categoryList = CategoryRepository.getCategoryList(groupId);
            assetPage = assetStoreRepository.getAssetListByGroupId(groupId, pageable);
            subCategoryList = new ArrayList<>();

        } 
        else if (groupId != null && categoryId != null && subCategoryId == 0) {

            categoryList = CategoryRepository.getCategoryList(groupId);
            subCategoryList = subCategoryRepository.getSubCategoryList(categoryId);
            assetPage = assetStoreRepository.getAssetListByGroupAndCategory(
                    groupId, categoryId, pageable);

        } 
        else if (groupId != null && categoryId != null && subCategoryId != null) {

            assetPage = assetStoreRepository.getAssetListByGroupCategorySubCategory(
                    groupId, categoryId, subCategoryId, pageable);

            categoryList = CategoryRepository.getCategoryList(groupId);
            subCategoryList = subCategoryRepository.getSubCategoryList(categoryId);

        } 
        else {

            subCategoryList = new ArrayList<>();
            categoryList = new ArrayList<>();

            assetPage = assetStoreRepository.getAssetStoreList(pageable);
        }

        List<AssetStore> assetStoreList = assetPage.getContent();

        /* -------- Custodian Logic -------- */

        for (AssetStore asset : assetStoreList) {

            AssetAllotment allotment =
                    assetAllotmentRepository.getAssetAllotment(asset.getStoreId());

            if (allotment == null) {

                asset.setCustodian("----Not Allotted----");

            } else {

                if (allotment.getAllottedTo() == null) {

                    asset.setCustodian(
                            allotment.getDepartment().getHierarchyName());

                } else {

                    asset.setCustodian(
                            allotment.getAllottedTo().getUser().getUserName());
                }

                asset.setAllottedOn(allotment.getAllottedOn());
            }
        }

        AssetStoreResponse response = new AssetStoreResponse();
        response.setGroupList(groupList);
        response.setCategoryList(categoryList);
        response.setSubCategoryList(subCategoryList);
        response.setAssetStoreList(assetStoreList);

        return response;
    }

    
    @Transactional
    public AssetAllotmentResponse getAllotments(Integer groupId,
            Integer categoryId,
            Integer subCategoryId,
            Integer officeId) {

List<AssetStore> assetStoreList;
List<SetupCategory> categoryList;
List<SetupSubCategory> subCategoryList;
List<SetupGroup> groupList = groupRepository.findAll();
List<HierarchySetup> assetOfficeList = hierarchySetupRepository.findAll();

if (groupId != null) {

categoryList = CategoryRepository.getCategoryList(groupId);

if (categoryId != null && categoryId != 0) {
subCategoryList = subCategoryRepository.getSubCategoryList(categoryId);
} else {
subCategoryList = new ArrayList<>();
}

assetStoreList =
assetStoreRepository.getAssetListBasedOnFilters(
groupId,
categoryId == null ? 0 : categoryId,
subCategoryId == null ? 0 : subCategoryId,
officeId == null ? 0 : officeId
);

} else {

categoryList = new ArrayList<>();
subCategoryList = new ArrayList<>();

assetStoreList = assetStoreRepository.getAssetStoreList();
}

List<AssetStore> updatedList = new ArrayList<>();

for (AssetStore assetstore : assetStoreList) {

AssetAllotment assetAllotment =
assetAllotmentRepository.getAssetAllotment(assetstore.getStoreId());

if (assetAllotment == null) {

assetstore.setAllotedTo("----Not Allotted----");
assetstore.setCustodian("----Not Allotted----");
updatedList.add(assetstore);
continue;
}

String allotedToName = "----Not Allotted----";
String custodianName = "Admin";

if (assetAllotment.getAllottedTo() == null) {

if (assetAllotment.getDepartment() != null) {
allotedToName =
assetAllotment.getDepartment().getHierarchyName();
}

} else if (assetAllotment.getAllottedTo().getUser() != null) {

UserSetup user =
assetAllotment.getAllottedTo().getUser();

allotedToName =
(user.getUserName() != null ? user.getUserName() : "") +
(user.getLastName() != null ? " " + user.getLastName() : "");
}

if (assetAllotment.getCustodian() != null &&
assetAllotment.getCustodian().getUser() != null) {

UserSetup custUser =
assetAllotment.getCustodian().getUser();

custodianName =
(custUser.getUserName() != null ? custUser.getUserName() : "") +
(custUser.getLastName() != null ? " " + custUser.getLastName() : "");
}

assetstore.setAllotedTo(allotedToName);
assetstore.setCustodian(custodianName);
assetstore.setAllottedOn(assetAllotment.getAllottedOn());

updatedList.add(assetstore);
}

AssetAllotmentResponse response = new AssetAllotmentResponse();

response.setGroupList(groupList);
response.setCategoryList(categoryList);
response.setSubCategoryList(subCategoryList);
response.setAssetOfficeList(assetOfficeList);
response.setAssetStoreList(updatedList);

return response;
}



    @Autowired
    private AssetRepository assetRepository;

    public Asset insertOrUpdateAsset(Asset asset, Integer officeId, Integer seatUserId) {

        asset.setOfficeId(officeId);
        asset.setDeleteStatus("N");
        asset.setAllotStatus("N");
        asset.setQuantityAllotted(0);

        SeatUserAlloted seat = new SeatUserAlloted();
        seat.setAllotId(seatUserId);
        asset.setActionSeatUser(seat);
        asset.setQuantity(1);

        return assetRepository.save(asset);
    }


    @Transactional
    public List<AssetInformationVo> getcompleteAssetInformation(Integer storeId) {

        List<AssetStore> assetStoreList = new ArrayList<>();

        if (storeId != null) {
            assetStoreList = assetStoreRepository.findByStoreId(storeId);
        }

        List<AssetFlow> assetFlowList = new ArrayList<>();
        if (storeId != null) {
            assetFlowList = assetFlowRepository.findAllBystoreId(storeId);
        }

        List<AMCDetails> amcDetailsList = new ArrayList<>();

        if (!assetStoreList.isEmpty()) {

            AssetStore assetStore = assetStoreList.get(0);

            if (assetStore.getAsset() != null &&
                "Y".equals(assetStore.getAsset().getAmc())) {

                amcDetailsList = amcDetailsRepository
                        .getAMCListBasedOnStoreId(storeId);
            }
        }

        return null;
    }


    public AllotListVo getListForAllot(Integer storeId, Integer officeId) {

        List<AssetStore> assetStore = new ArrayList<>();

        if (storeId != null) {
            assetStore = assetStoreRepository.findByStoreId(storeId);
        }

        List<HierarchySetup> assetOfficeList =
                hierarchySetupRepository.getOfficeListforAssets();

        List<SeatUserAlloted> userList =
                SeatUserAllotedRepository.getAllUsersUnderCurrentOffice(officeId);

        AllotListVo vo = new AllotListVo();

        vo.setStoreId(storeId);
        vo.setOfficeId(officeId);
        vo.setAssetStore(assetStore);
        vo.setAssetOfficeList(assetOfficeList);
        vo.setUserList(userList);

        return vo;
    }
    

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void returnTransferedAssetToStore(ReturnAssetRequest request) throws ParseException {

        Date actionDate = sdf.parse(request.getActionDate());

        AssetTransfer assetTransfer = new AssetTransfer();

        // set store
        AssetStore store = new AssetStore();
        store.setStoreId(request.getStoreId());
        assetTransfer.setStore(store);

        // set office
        HierarchySetup office = new HierarchySetup();
        office.setHierarchyId(request.getOfficeId());
        assetTransfer.setOffice(office);

        // set transferedFrom (REQUIRED)
        HierarchySetup transferedFrom = new HierarchySetup();
        transferedFrom.setHierarchyId(request.getOfficeId());
        assetTransfer.setTransferedFrom(transferedFrom);
        assetTransfer.setAcknowledgementStatus("N");  // example default
        assetTransfer.setReturnedOn(actionDate);
        assetTransfer.setReturnRemarks(request.getReturnRemarks());

        saveAssetTransfer(assetTransfer);
    }
    
    public AssetAllotment assetAllottedList(Integer storeId) {
        return assetAllotmentRepository.findByStore_StoreId(storeId);
    }
    
    public void allotAsset(AssetAllotment assetAllotment) { assetAllotmentRepository.save(assetAllotment); }
    
    public void changeStoreStatus(Integer storeId, String status) {

        AssetStore store = assetStoreRepository
                .findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setStatus(status);

        assetStoreRepository.save(store);
    }
    
    public void saveAssetTransfer(AssetTransfer assetTransfer) {
        assetTransferRepository.save(assetTransfer);
    }
    
    public List<AssetStore> getAssetMaintenancePage() {

        List<AssetStore> assetStoreList =
                assetStoreRepository.getAssetMaintanceListByStatus();

        assetStoreList.stream().forEach(assetStore -> {
            AssetMaintenance assetMaintenance =
                    assetMaintenanceRepository.getAssetMaintanaceBasedonStoreId(assetStore.getStoreId());  
            if (assetMaintenance != null) {
                assetStore.setMaintenanceDate(assetMaintenance.getComplaintDate());
            }
        });

        return assetStoreList;
    }


    public MaintenanceViewResponse getMaintananceViewModal(Integer storeId) {

        MaintenanceViewResponse response = new MaintenanceViewResponse();

        Optional<AssetStore> assetStore =
                assetStoreRepository.findById(storeId);

        AssetMaintenance assetMaintenance =
                assetMaintenanceRepository
                        .getAssetMaintanaceBasedonStoreId(storeId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (assetMaintenance != null &&
            assetMaintenance.getAssetMaintenanceId() != null) {

            response.setComplaintDate(
                    sdf.format(assetMaintenance.getComplaintDate()));

            if (assetMaintenance.getMaintenanceDate() != null) {

                response.setMaintenanceDate(
                        sdf.format(assetMaintenance.getMaintenanceDate()));
            }

        } else {

            assetMaintenance = new AssetMaintenance();
            assetMaintenance.setStoreId(storeId);
        }

        List<AssetMaintenance> assetMaintenanceList =
                assetMaintenanceRepository
                        .getAssetMaintanceBasedOnHistory(storeId);

        response.setAssetStore(assetStore.orElse(null));
        response.setAssetMaintenance(assetMaintenance);
        response.setAssetMaintenanceList(assetMaintenanceList);

        return response;
    }


    public Page<AssetStore> getAssetDisposalList(String assetCode, Pageable pageable) {

        Page<AssetStore> assetPage =
                assetStoreRepository.getAssetDisposalListByStatus(pageable);

        List<AssetStore> assetStoreList = assetPage.getContent();

        assetStoreList.forEach(assetStore -> {

            AssetDisposal assetDisposal =
                    assetDisposalRepository.getAssetDisposalBasedonStoreId(assetStore.getStoreId());

            if (assetDisposal != null) {
                assetStore.setDisposalDate(assetDisposal.getDisposedDate());
                assetStore.setDisposalAmount(assetDisposal.getDisposalCost());
            }

        });

        if (assetCode != null && !assetCode.isEmpty()) {

            Map<String, AssetStore> assetMap = new HashMap<>();

            for (AssetStore asset : assetStoreList) {
                assetMap.put(asset.getAssetCode(), asset);
            }

            AssetStore result = assetMap.get(assetCode);

            if (result != null) {
                return new PageImpl<>(List.of(result), pageable, 1);
            } else {
                return new PageImpl<>(new ArrayList<>(), pageable, 0);
            }
        }

        return new PageImpl<>(assetStoreList, pageable, assetPage.getTotalElements());
    }
    
    

	@Autowired
	private AMCPeriodRepository aMCPeriodRepository;

	public List<AmcVo> getAmcList(){

	    List<AMCPeriod> amcList = aMCPeriodRepository.findAll();

	    List<AmcVo> result = new ArrayList<>();

	    for(AMCPeriod amc : amcList){

	        AmcVo vo = new AmcVo();

	        vo.setPeriodFrom(amc.getPeriodFrom());
	        vo.setPeriodTo(amc.getPeriodTo());
	        vo.setAmount(amc.getAmount());
	        vo.setVendorName(amc.getName());

	        
	        result.add(vo);
	    }
	    
	    List<AssetStore> assetStoresList = assetStoreRepository.getAmcbystatus();

	    AmcVo vo = new AmcVo();
	    vo.setAssetStoresList(assetStoresList);
	    return result;
	}


	@Autowired
	private AMCVisitRepository aMCVisitRepository;

	public List<AMCVisit> getAmcVisitList() {

	    List<AMCVisit> amcVisitList = aMCVisitRepository.findAll();

	    return amcVisitList;
	}


	public List<AssetStore> getAssetCode() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
    



