package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.Srs;

@Repository
public interface SrsRepository extends JpaRepository<Srs, Integer> {

    @Query("SELECT s FROM Srs s " +
           "WHERE s.crs.crsId = :crsId " +
           "AND (s.deleteStatus IS NULL OR s.deleteStatus = 'N') " +
           "ORDER BY s.srsId ASC")
    List<Srs> findByCrsId(@Param("crsId") Integer crsId);

//	List<Srs> findByCrsIds(List<Integer> crsIds);
    
    @Query("SELECT s FROM Srs s WHERE s.crs.crsId IN :crsIds")
    List<Srs> findByCrsIds(@Param("crsIds") List<Integer> crsIds);
}