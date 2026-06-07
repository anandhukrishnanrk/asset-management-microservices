package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.Crs;

@Repository
public interface CrsRepository extends JpaRepository<Crs, Integer> {

    @Query("SELECT c FROM Crs c " +
           "WHERE c.module.moduleId = :moduleId " +
           "AND c.deleteStatus = 'N' " +
           "ORDER BY c.crsId ASC")
    List<Crs> findByModuleId(@Param("moduleId") Integer moduleId);

//	List<Crs> findByModuleIds(List<Integer> moduleIds);
	
	@Query("SELECT c FROM Crs c WHERE c.module.moduleId IN :moduleIds")
    List<Crs> findByModuleIds(@Param("moduleIds") List<Integer> moduleIds);
}
