package com.asset.asset_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asset.asset_api.entity.UserSetup;
@Repository
public interface UserSetupRepository extends JpaRepository<UserSetup, Long> {

    Optional<UserSetup> findByUserName(String userName);

}
