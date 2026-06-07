package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cis.api.entity.PmpProjectEscalations;

public interface PmpProjectEscalationsRepository extends JpaRepository<PmpProjectEscalations, Integer>{

	List<PmpProjectEscalations> findByPmpId(Integer pmpId);

}
