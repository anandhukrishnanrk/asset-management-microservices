package com.cis.api.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cis.api.entity.DeliverableSchedulesDetails;

public interface DeliverableSchedulesDetailsRepository extends JpaRepository<DeliverableSchedulesDetails, Integer> {
    List<DeliverableSchedulesDetails> findByDeliverableSchedulesDeffortId(Integer dEffortId);
}
