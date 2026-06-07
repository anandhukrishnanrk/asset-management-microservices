package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.BugType;

@Repository
public interface BugTypeRepository extends JpaRepository<BugType, Integer> {

    List<BugType> findByDeleteStatus(String deleteStatus);
}
