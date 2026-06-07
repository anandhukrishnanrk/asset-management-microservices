package com.cis.api.dto;


import java.util.List;
import lombok.Data;

@Data
public class AssetAllotmentResponse {

    private List<GroupDTO> groupList;
    private List<CategoryDTO> categoryList;
    private List<SubCategoryDTO> subCategoryList;
    private List<OfficeDTO> assetOfficeList;
    private List<AssetStoreDTO> assetStoreList;

}
