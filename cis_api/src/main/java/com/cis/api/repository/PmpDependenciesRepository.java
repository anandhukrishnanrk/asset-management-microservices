package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.PmpDependencies;


@Repository
public interface PmpDependenciesRepository extends JpaRepository<PmpDependencies, Integer> {

    List<PmpDependencies> findByPmpId(Integer pmpId);
}