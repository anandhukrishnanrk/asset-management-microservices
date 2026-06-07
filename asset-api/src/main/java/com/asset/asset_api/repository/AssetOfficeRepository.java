package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.asset.asset_api.entity.AssetOffice;

public interface AssetOfficeRepository {

	
	@Query(""" 
			from  as hs where hs.category in ('G')
			""")
	 List<AssetOffice> getOfficeListforAssets(); 
	

}
