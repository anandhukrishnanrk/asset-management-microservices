package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ChangeLogBase;

@Repository
public interface ChangeLogBaseRepository extends JpaRepository<ChangeLogBase, Long> {
    Collection<ChangeLogBase> findAllByProjectProjectIdOrderByRequestDateDesc(Long projectId);

    Page<ChangeLogBase> findAllByProjectProjectId(Long projectId, Pageable pageable);
}
