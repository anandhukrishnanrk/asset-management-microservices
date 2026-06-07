package com.cis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.DddUploadDetails;

@Repository
public interface DddUploadDetailsRepository extends JpaRepository<DddUploadDetails, Long> {
    DddUploadDetails findByDddDddId(Long ddId);
}
