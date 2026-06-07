package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.PointsofContact;

public interface PointsofContactRepository extends JpaRepository<PointsofContact, Integer> {

    @Query("""
        select a from PointsofContact a
        where a.status = 'kran'
        and a.pmp.gdReleasedId = :pmpid
    """)
    List<PointsofContact> findByPmpId(@Param("pmpid") Integer pmpId);


    @Query("""
        select a from PointsofContact a
        where a.status = 'client'
        and a.pmp.gdReleasedId = :pmpid
    """)
    List<PointsofContact> findByPmpIdContactClient(@Param("pmpid") Integer pmpId);
}