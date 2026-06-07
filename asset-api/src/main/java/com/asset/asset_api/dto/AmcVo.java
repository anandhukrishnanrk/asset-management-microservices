package com.asset.asset_api.dto;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.asset.asset_api.entity.AssetStore;

import lombok.Data;

@Data
public class AmcVo {

    private Integer storeId;
    private String assetCode;
    private String serialNumber;

    private Date periodFrom;
    private Date periodTo;

    private String vendorName;
    private String vendorAddress;

    private Double amount;
    private String status;
	public void setAmount(BigDecimal amount2) {
		// TODO Auto-generated method stub
		
	}
	
    private List<AssetStore> assetStoresList;
}