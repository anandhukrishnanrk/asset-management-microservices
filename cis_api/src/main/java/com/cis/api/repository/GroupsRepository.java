package com.cis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ProjectGroup;

@Repository
public interface GroupsRepository extends JpaRepository<ProjectGroup, Integer> {

	@Query("""
		    SELECT MIN(pg.id)
		    FROM ProjectGroup pg
		    WHERE pg.project.projectId = :projectId
		""")
		Integer findMinGroupIdByProject(@Param("projectId") Integer projectId);
}
