package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.SetupDeliverableType;

public interface SetupDeliverableTypeRepository extends JpaRepository<SetupDeliverableType, Long> {

    @Query(value = "FROM SetupDeliverableType s WHERE s.activeStatus = 'Y' OR s.activeStatus IS NULL ORDER BY s.deliverableType")
    List<SetupDeliverableType> findAllActive();
}
