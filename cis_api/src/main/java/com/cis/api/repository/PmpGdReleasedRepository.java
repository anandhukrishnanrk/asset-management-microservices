package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.PmpGdReleased;

@Repository
public interface PmpGdReleasedRepository extends JpaRepository<PmpGdReleased, Long> {
    Collection<PmpGdReleased> findByTypeAndProjectIdOrderByGdReleasedIdDesc(String type, Integer projectId);

	static Page<PmpGdReleased> findPmpDetailsBYpIdandgId(Integer pId, Integer gId) {
		
		return null;
	}
}
