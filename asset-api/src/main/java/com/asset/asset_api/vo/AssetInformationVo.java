package com.asset.asset_api.vo;

import java.util.List;

import com.asset.asset_api.entity.AMCDetails;
import com.asset.asset_api.entity.AssetFlow;
import com.asset.asset_api.entity.AssetStore;

import lombok.Data;

@Data
public class AssetInformationVo {

    private List<AssetStore> assetStoreList;

    private List<AssetFlow> assetFlowList;

    private List<AMCDetails> amcDetailsList;

}