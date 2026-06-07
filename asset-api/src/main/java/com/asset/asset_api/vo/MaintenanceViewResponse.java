package com.asset.asset_api.vo;

import java.util.List;

import com.asset.asset_api.entity.AssetMaintenance;
import com.asset.asset_api.entity.AssetStore;

import lombok.Data;
@Data
public class MaintenanceViewResponse {

    private AssetStore assetStore;
    private AssetMaintenance assetMaintenance;
    private List<AssetMaintenance> assetMaintenanceList;

    private String complaintDate;
    private String maintenanceDate;

    // getters setters
}
