package com.cis.api.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ProjectDetails;

@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Long> {

	@Query(value = "SELECT * FROM pts.project_details WHERE active_Status = :activeStatus", nativeQuery = true)
	Collection<ProjectDetails> findAllByActiveStatus(@Param("activeStatus") String activeStatus);

	Collection<ProjectDetails> findAllByClientRegisterId(Long clientId);

	Collection<ProjectDetails> findAllByActiveStatusAndBillableStatusOrderByProjectIdDesc(String activeStatus, String billableStatus);

	// @Query("select distinct p from ProjectDetails p where
	// p.clientRegister.clientRegisterId = :id")
	// Optional<ProjectDetails> findByIdWithClient(Long id);

}
