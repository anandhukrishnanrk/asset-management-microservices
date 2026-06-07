package com.cis.api.repository;

import com.cis.api.entity.WBSVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WBSVersionRepository extends JpaRepository<WBSVersion, Long> {
    List<WBSVersion> findByProjectProjectId(Long projectId);
}
