package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ChangeLogDetails;

@Repository
public interface ChangeLogDetailsRepository extends JpaRepository<ChangeLogDetails, Long> {
    Collection<ChangeLogDetails> findAllByBaseVerIdOrderByDetIdDesc(Long baseId);

    Page<ChangeLogDetails> findAllByBaseVerId(Long baseId, Pageable pageable);
}
