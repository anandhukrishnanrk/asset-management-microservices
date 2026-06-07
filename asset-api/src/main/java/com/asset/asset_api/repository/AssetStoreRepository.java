package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.asset_api.entity.AssetStore;



public interface AssetStoreRepository extends JpaRepository<AssetStore, Integer> {

    @Query("FROM AssetStore a WHERE a.asset.group.groupId = :groupId AND a.status = 'S'")
    Page<AssetStore> getAssetListByGroupId(
            @Param("groupId") Integer groupId,
            Pageable pageable);

    @Query("""
           FROM AssetStore a 
           WHERE a.asset.group.groupId = :groupId
           AND a.status = 'S'
           AND a.asset.category.categoryId = :categoryId
           """)
    Page<AssetStore> getAssetListByGroupAndCategory(
            @Param("groupId") Integer groupId,
            @Param("categoryId") Integer categoryId,
            Pageable pageable);

    @Query("""
           FROM AssetStore a 
           WHERE a.asset.group.groupId = :groupId
           AND a.status = 'S'
           AND a.asset.category.categoryId = :categoryId
           AND a.asset.subCategory.subCategoryId = :subCategoryId
           """)
    Page<AssetStore> getAssetListByGroupCategorySubCategory(
            @Param("groupId") Integer groupId,
            @Param("categoryId") Integer categoryId,
            @Param("subCategoryId") Integer subCategoryId,
            Pageable pageable);

    @Query("FROM AssetStore")
    Page<AssetStore> getAssetStoreList(Pageable pageable);

    @Query("FROM AssetStore")
    List<AssetStore> getAssetStoreList();
    
    
	@Query("""
       FROM AssetStore a
       WHERE a.asset.group.groupId = :groupId
       AND a.status = 'A'
       AND (:categoryId IS NULL OR :categoryId = 0 OR a.asset.category.categoryId = :categoryId)
       AND (:subCategoryId IS NULL OR :subCategoryId = 0 OR a.asset.subCategory.subCategoryId = :subCategoryId)
       AND (:officeId IS NULL OR :officeId = 0 OR a.office.hierarchyId = :officeId)
       """)
List<AssetStore> getAssetListBasedOnGroupIdInAllot(
        @Param("groupId") Integer groupId,
        @Param("categoryId") Integer categoryId,
        @Param("subCategoryId") Integer subCategoryId,
        @Param("officeId") Integer officeId);
	
	@Query("""
		       FROM AssetStore a
		       WHERE a.asset.group.groupId = :groupId
		       AND a.status = 'A'
		       AND (:categoryId = 0 OR a.asset.category.categoryId = :categoryId)
		       AND (:subCategoryId = 0 OR a.asset.subCategory.subCategoryId = :subCategoryId)
		       AND (:officeId = 0 OR a.office.hierarchyId = :officeId)
		       """)
		List<AssetStore> getAssetListBasedOnFilters(
		        @Param("groupId") Integer groupId,
		        @Param("categoryId") Integer categoryId,
		        @Param("subCategoryId") Integer subCategoryId,
		        @Param("officeId") Integer officeId);

    List<AssetStore> findByStoreId(Integer storeId);

    
    @Query("""
    		from AssetStore as a where a.status='M'
    		""")
	List<AssetStore> getAssetMaintanceListByStatus();

    @Query("""
    	       from AssetStore a where a.status='D'
    	       """)
    	Page<AssetStore> getAssetDisposalListByStatus(Pageable pageable);

    
    @Query("""
            from AssetStore a where a.asset.amc = 'M'
           """)
    List<AssetStore> getAmcbystatus();
}

