package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.asset_api.entity.SetupSubCategory;

public interface SubCategoryRepository extends JpaRepository<SetupSubCategory, Integer>{

	@Query("""
	           FROM SetupSubCategory a
	           WHERE a.deleteStatus = 'N'
	           AND (:categoryId IS NULL OR a.category.categoryId = :categoryId)
	           """)
	    List<SetupSubCategory> getSubCategoryList(@Param("categoryId") Integer categoryId);
}
