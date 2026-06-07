package com.cis.api.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class AssetAllotResponceDTO {

    private Integer storeId;
    private Integer officeId;

    private List<Map<String, Object>> assetStore;
    private List<Map<String, Object>> assetOfficeList;
    private List<Map<String, Object>> userList;
}




