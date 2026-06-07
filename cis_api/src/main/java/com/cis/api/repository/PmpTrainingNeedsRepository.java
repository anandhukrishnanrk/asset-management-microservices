package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.PmpTrainingNeeds;
import com.cis.api.entity.ResourceAllocationDetails;

public interface PmpTrainingNeedsRepository extends JpaRepository<PmpTrainingNeeds, Integer> {


	List<PmpTrainingNeeds> findByPmpId(Integer pmpId);
}
