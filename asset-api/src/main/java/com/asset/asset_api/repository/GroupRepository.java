package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asset.asset_api.entity.SetupGroup;


@Repository
public interface GroupRepository extends JpaRepository<SetupGroup, Integer> {

}
	


