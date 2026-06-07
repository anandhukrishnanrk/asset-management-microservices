package com.asset.asset_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asset.asset_api.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer>{

	Asset save(Asset asset);

}
