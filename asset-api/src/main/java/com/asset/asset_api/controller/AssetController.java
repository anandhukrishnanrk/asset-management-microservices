package com.asset.asset_api.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asset.asset_api.dto.AssetStoreResponse;
import com.asset.asset_api.dto.ReturnAssetRequest;
import com.asset.asset_api.entity.AMCVisit;
import com.asset.asset_api.entity.Asset;
import com.asset.asset_api.entity.AssetStore;
import com.asset.asset_api.service.AssetService;
import com.asset.asset_api.vo.AllotListVo;
import com.asset.asset_api.vo.AssetAllotmentResponse;
import com.asset.asset_api.vo.AssetInformationVo;
import com.asset.asset_api.vo.MaintenanceViewResponse;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/asset-details")
@CrossOrigin(origins = "*")

public class AssetController<AmcVo> {
	
	@Autowired
	private AssetService assetService;
	
	@GetMapping("/ping")
	public String ping() {                           
		return "ProjectDetailsController is up!";
	}
	
	@GetMapping("/test")
	public String test() {
	    System.out.println("Asset API HIT");
	    return "Asset API Working";
	}
	
	@GetMapping("/store")
	public ResponseEntity<AssetStoreResponse> getAssetStore(

	        @RequestParam(required = false) Integer groupId,
	        @RequestParam(required = false) Integer categoryId,
	        @RequestParam(required = false) Integer subCategoryId,

	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "15") int size
	) {

	    AssetStoreResponse response = assetService.getAssetStore(
	            groupId, categoryId, subCategoryId, page, size);

	    return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping("/allotments")
    public ResponseEntity<AssetAllotmentResponse> getAllotments(
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer subCategoryId,
            @RequestParam(required = false) Integer officeId) {

        AssetAllotmentResponse response =
                assetService.getAllotments(groupId, categoryId, subCategoryId, officeId);

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/save-registration")
    public ResponseEntity<Asset> saveAsset(
            @RequestBody Asset asset,
            @RequestParam Integer officeId,
            @RequestParam Integer seatUserId) {

        Asset savedAsset =
                assetService.insertOrUpdateAsset(asset, officeId, seatUserId);

        return ResponseEntity.ok(savedAsset);
    }
	
	@GetMapping("/complete-asset-information")
	public List<AssetInformationVo> completeAssetInformation(
            @RequestParam(required = true) Integer storeId
			)
	{
	return assetService.getcompleteAssetInformation(storeId);
	}
	
	@GetMapping("/allot-list")
	public AllotListVo listForAllot(
	        @RequestParam Integer storeId,
	        @RequestParam Integer officeId) {

	    return assetService.getListForAllot(storeId, officeId);
	}
	
	
	@PostMapping("/return-transfer-asset")
    public String returnTransferedAssetToStore(@RequestBody ReturnAssetRequest request)
            throws ParseException {

        assetService.returnTransferedAssetToStore(request);

        return "SUCCESS";
    }
	
	 @GetMapping("/list-maintanance")
	    public List<AssetStore> getAssetMaintenanceList() {
	        return assetService.getAssetMaintenancePage();
	    }
	 
	 @GetMapping("/maintanance-view-modal")
		public MaintenanceViewResponse maintananceViewModal(
		        @RequestParam Integer storeId
		        ) {

		    return assetService.getMaintananceViewModal(storeId);
		}
	
//	 @GetMapping("/list-disposal")
//	    public List<AssetStore> getAssetDisposalList() {
//	        return assetService.getAssetDisposalList();
//	    }
	 
	 
	 @GetMapping("/list-disposal")
	 public Page<AssetStore> getAssetDisposalList(
	         @RequestParam(required = false) String assetCode,
	         @PageableDefault(size = 15) Pageable pageable) {

	     return assetService.getAssetDisposalList(assetCode, pageable);
	 }
	 
	 @GetMapping("/amc")
	 public List<AmcVo> getAmcList(){
	     return (List<AmcVo>) assetService.getAmcList();
	 }
	 
	 @GetMapping("/amc-visit")
	 public List<AMCVisit> getAmcVisitList(){
	     return  assetService.getAmcVisitList();
	 }
	 
	 @GetMapping("/amc-visit/assetCode")
	 public List<AssetStore> searchAssetCode(){
	     return  assetService.getAssetCode();
	 }
	 
	 

}
