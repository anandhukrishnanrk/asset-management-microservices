package com.cis.api.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.ImpDetails;

@Repository
public interface ImpDetailsRepository extends JpaRepository<ImpDetails, Integer> {

    Collection<ImpDetails> findAllByProjectProjectIdAndDeleteStatus(Integer projectId, String deleteStatus);

}
