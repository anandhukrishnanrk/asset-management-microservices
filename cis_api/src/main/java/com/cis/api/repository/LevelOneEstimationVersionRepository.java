package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.LevelOneEstimationVersion;

@Repository
public interface LevelOneEstimationVersionRepository extends JpaRepository<LevelOneEstimationVersion, Integer> {
    Collection<LevelOneEstimationVersion> findAllByProjectIdOrderByLevelOneEstimationVersionIdDesc(Integer projectId);
}
