package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.SupportingDocuments;

@Repository
public interface SupportingDocumentsRepository extends JpaRepository<SupportingDocuments, Long> {
    Collection<SupportingDocuments> findAllByCmmiProjectIdAndDeleteStatusOrderBySubmissionDateDesc(Long cmmiProjectId, String deleteStatus);
}
