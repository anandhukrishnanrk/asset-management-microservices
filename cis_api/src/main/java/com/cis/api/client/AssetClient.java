package com.cis.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cis.api.dto.AssetAllotResponceDTO;
import com.cis.api.dto.AssetAllotmentResponse;
import com.cis.api.dto.AssetStoreResponseDTO;

import java.util.List;

@FeignClient(name = "ASSET-API")
public interface AssetClient {

    @GetMapping("/api/asset-details/test")
    String test();
    

    @GetMapping("/asset-api/api/asset-details/store")
    AssetStoreResponseDTO getAssetStore(
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer subCategoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    );
    
    @GetMapping("/asset-api/api/asset-details/allotments")
    AssetAllotmentResponse getAllotments(
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer subCategoryId,
            @RequestParam(required = false) Integer officeId
    );

    @GetMapping("/asset-api/api/asset-details/allot-list")

	AssetAllotResponceDTO getAssetlist(
			 @RequestParam(required = false) Integer storeId,
	            @RequestParam(required = false) Integer officeId,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "15") int size
);
}