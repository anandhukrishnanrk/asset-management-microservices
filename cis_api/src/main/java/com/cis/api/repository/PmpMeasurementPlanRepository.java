package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.PmpDevelopmentInfrastructure;
import com.cis.api.entity.PmpMeasurementPlan;

public interface PmpMeasurementPlanRepository     extends JpaRepository<PmpMeasurementPlan, Integer> {

@Query("""
       from PmpMeasurementPlan mp
       where mp.pmpId = :pmpId
       order by mp.metricsSetup.displayOrder
       """)
List<PmpMeasurementPlan> findByPmpIdAndMetricsSetup(
        @Param("pmpId") Integer pmpId);
}
