package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cis.api.entity.PmpDevelopmentInfrastructure;

public interface PmpDevelopmentInfrastructureRepository extends JpaRepository<PmpDevelopmentInfrastructure, Integer> {

	List<PmpDevelopmentInfrastructure> findByPmpId(Integer pmpId);

}
