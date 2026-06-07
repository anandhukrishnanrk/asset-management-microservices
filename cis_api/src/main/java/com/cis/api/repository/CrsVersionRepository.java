package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.CrsVersion;

@Repository
public interface CrsVersionRepository extends JpaRepository<CrsVersion, Integer> {
    Collection<CrsVersion> findByProjectIdOrderByCrsVersionIdDesc(Integer projectId);
}
