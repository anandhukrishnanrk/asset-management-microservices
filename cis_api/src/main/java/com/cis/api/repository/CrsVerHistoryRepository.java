package com.cis.api.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.CrsVerHistory;
import com.cis.api.entity.CrsVersion;

@Repository
public interface CrsVerHistoryRepository extends JpaRepository<CrsVerHistory, Integer> {
    List<CrsVerHistory> findByCrsVerCrsVersionIdOrderByCrsVerHisIdAsc(Integer crsVerId);
}




