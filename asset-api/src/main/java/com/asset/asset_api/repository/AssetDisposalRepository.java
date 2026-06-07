package com.asset.asset_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.asset_api.entity.AssetDisposal;

public interface AssetDisposalRepository extends JpaRepository<AssetDisposal, Integer>{

    @Query("""
    	    from AssetDisposal a 
    	    where a.storeId = :storeId 
    	    """)
	 AssetDisposal getAssetDisposalBasedonStoreId(Integer storeId); 
	

	
	


}
