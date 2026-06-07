package com.cis.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cis.api.entity.NewAttachments;



public interface NewAttachmentsRepository extends JpaRepository<NewAttachments, Integer> {

    Page<NewAttachments> findByCmmiProjectIdAndAttachmentTypeAndDeleteStatusOrderBySubmissionDateDesc(
            Long cmmiProjectId,
            String attachmentType,
            String deleteStatus,
            Pageable pageable
    );
}

