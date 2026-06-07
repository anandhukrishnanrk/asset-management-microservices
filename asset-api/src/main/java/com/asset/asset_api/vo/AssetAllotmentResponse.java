package com.asset.asset_api.vo;


import java.util.List;

import lombok.Data;

import com.asset.asset_api.entity.AssetStore;
import com.asset.asset_api.entity.HierarchySetup;
import com.asset.asset_api.entity.SetupCategory;
import com.asset.asset_api.entity.SetupGroup;
import com.asset.asset_api.entity.SetupSubCategory;

@Data
public class AssetAllotmentResponse {

    private List<SetupGroup> groupList;

    private List<SetupCategory> categoryList;

    private List<SetupSubCategory> subCategoryList;

    private List<HierarchySetup> assetOfficeList;

    private List<AssetStore> assetStoreList;

}
