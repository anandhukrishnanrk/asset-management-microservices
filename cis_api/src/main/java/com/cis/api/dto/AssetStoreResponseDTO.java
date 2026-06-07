package com.cis.api.dto;


import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class AssetStoreResponseDTO {

    private List<Map<String, Object>> assetStoreList;
    private List<Map<String, Object>> categoryList;
    private List<Map<String, Object>> subCategoryList;
    private List<Map<String, Object>> groupList;
}