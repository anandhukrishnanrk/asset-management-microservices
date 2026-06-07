package com.asset.asset_api.dto;

import lombok.Data;

@Data
public class ReturnAssetRequest {

    private Integer storeId;
    private String actionDate;
    private String returnRemarks;
    private Integer officeId;   // ✅ add this

    

}
