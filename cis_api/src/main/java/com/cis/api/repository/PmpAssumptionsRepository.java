package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.PmpAssumptions;

@Repository
public interface PmpAssumptionsRepository extends JpaRepository<PmpAssumptions, Integer> {

    List<PmpAssumptions> findByPmpId(Integer pmpId);
}