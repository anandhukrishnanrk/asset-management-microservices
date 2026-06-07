package com.cis.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ReleaseUploadDetails;

@Repository
public interface ReleaseUploadDetailsRepository extends JpaRepository<ReleaseUploadDetails, Long> {
    
	Page<ReleaseUploadDetails> findAllByReleaseProjectProjectIdOrderByReleaseSubmissionDateDesc(
            Long projectId, Pageable pageable);

    Page<ReleaseUploadDetails> findAllByReleaseProjectProjectId(Long projectId, Pageable pageable);
}
