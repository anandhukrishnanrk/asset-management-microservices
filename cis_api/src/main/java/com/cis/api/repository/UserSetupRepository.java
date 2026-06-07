package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.UserSetup;

public interface UserSetupRepository  extends JpaRepository<UserSetup, Integer>{

	@Query("SELECT u FROM UserSetup u WHERE u.activeStatus = 'Y'")
	List<UserSetup> findActiveUsers();

}
