package com.cis.api.dto;

import lombok.Data;

@Data
public class AssetStoreDTO {

    private Integer storeId;
    private String assetCode;
    private String status;
    private String custodian;
    private String allottedOn;

}
