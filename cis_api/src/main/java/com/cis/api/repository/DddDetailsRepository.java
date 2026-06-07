package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.DddDetails;

@Repository
public interface DddDetailsRepository extends JpaRepository<DddDetails, Long> {
    Collection<DddDetails> findAllByProjectIdOrderBySubmissionDateDesc(Long projectId);
}
