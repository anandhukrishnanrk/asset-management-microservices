package com.cis.api.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.ImpUploadDetails;

@Repository
public interface ImpUploadDetailsRepository extends JpaRepository<ImpUploadDetails, Integer> {

    Collection<ImpUploadDetails> findAllByImpProjectProjectIdAndImpImpStatusOrderByImpImpIdDesc(Integer projectId,
            String impStatus);

}
