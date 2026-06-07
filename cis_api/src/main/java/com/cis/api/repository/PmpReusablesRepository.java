package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cis.api.entity.PmpReusables;

public interface PmpReusablesRepository extends JpaRepository<PmpReusables, Integer>{

	List<PmpReusables> findByPmpId(Integer pmpId);

}
