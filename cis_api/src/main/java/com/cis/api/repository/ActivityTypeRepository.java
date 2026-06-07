package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.ActivityType;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {

    @Query(value = "FROM ActivityType a WHERE a.activeStatus = 'Y' OR a.activeStatus IS NULL ORDER BY a.typeName")
    List<ActivityType> findAllActive();

    List<ActivityType> findByTypeNameIgnoreCase(String typeName);
}
