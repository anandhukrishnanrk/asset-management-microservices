package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.SetupDeliverableFrequency;

public interface SetupDeliverableFrequencyRepository extends JpaRepository<SetupDeliverableFrequency, Long> {

    @Query(value = "FROM SetupDeliverableFrequency s WHERE s.activeStatus = 'Y' OR s.activeStatus IS NULL")
    List<SetupDeliverableFrequency> findAllActive();
}