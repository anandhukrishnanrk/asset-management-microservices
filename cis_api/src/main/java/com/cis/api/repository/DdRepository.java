package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.Dd;

@Repository
public interface DdRepository extends JpaRepository<Dd, Integer> {
    @Query(value = "SELECT * FROM pts.dd WHERE project_Id = :projectId ORDER BY STR_TO_DATE(dd_Date_Discussion ,'%d-%m-%Y') DESC", nativeQuery = true)
    Collection<Dd> findAllByProjectIdOrderByDdDateDiscussionDesc(@Param("projectId") Integer projectId);
}
