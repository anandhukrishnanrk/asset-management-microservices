package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.DeliverableSchedules;

@Repository
public interface DeliverableSchedulesRepository extends JpaRepository<DeliverableSchedules, Integer> {
    Collection<DeliverableSchedules> findAllByProjectProjectIdAndDeleteStatus(Integer projectId, String deleteStatus);
}
