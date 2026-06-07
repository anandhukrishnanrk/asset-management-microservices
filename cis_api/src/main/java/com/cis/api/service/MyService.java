package com.cis.api.service;

import com.cis.api.client.AssetClient;
import com.cis.api.dto.AssetAllotResponceDTO;
import com.cis.api.dto.AssetAllotmentResponse;
import com.cis.api.dto.AssetStoreResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private AssetClient assetClient;

    public String callAsset() {
        String data = assetClient.test();   // ✅ correct method
        System.out.println("Response from asset-api: " + data);
        return data;
    }
    
    public AssetStoreResponseDTO getStoreData() {

        AssetStoreResponseDTO response =
                assetClient.getAssetStore(null, null, null, 0, 15);

        return response;
    }
    
    public AssetAllotmentResponse getAllotmentsData(
            Integer groupId,
            Integer categoryId,
            Integer subCategoryId,
            Integer officeId) {

        return assetClient.getAllotments(groupId, categoryId, subCategoryId, officeId);
    }

	public AssetAllotResponceDTO getAllotList() {
		AssetAllotResponceDTO response=
				assetClient.getAssetlist(1,1,0,15);
		return response;
	}
    
}