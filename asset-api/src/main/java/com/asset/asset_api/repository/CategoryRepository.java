package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.asset_api.entity.SetupCategory;

public interface CategoryRepository extends JpaRepository<SetupCategory, Integer>{

	@Query("FROM SetupCategory a WHERE a.group.groupId = :groupId")
	static
    List<SetupCategory> getCategoryList(@Param("groupId") Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}		
	}


