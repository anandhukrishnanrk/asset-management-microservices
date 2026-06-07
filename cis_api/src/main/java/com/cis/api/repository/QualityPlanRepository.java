package com.cis.api.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.QualityPlan;

@Repository
public interface QualityPlanRepository extends JpaRepository<QualityPlan, Integer> {

    Collection<QualityPlan> findAllByCmmiProjectIdAndDeleteStatus(Long projectId, String deleteStatus);

}
