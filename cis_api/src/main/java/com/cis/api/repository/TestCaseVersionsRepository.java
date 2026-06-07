package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.TestCaseVersions;

@Repository
public interface TestCaseVersionsRepository extends JpaRepository<TestCaseVersions, Long> {
    Collection<TestCaseVersions> findAllByProjectIdOrderByVersionIdDesc(Long projectId);
}
