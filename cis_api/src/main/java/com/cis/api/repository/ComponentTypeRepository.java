package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.ComponentType;

public interface ComponentTypeRepository extends JpaRepository<ComponentType, Long> {

    @Query(value = "FROM ComponentType c WHERE c.activeStatus = 'Y' OR c.activeStatus IS NULL ORDER BY c.typeName")
    List<ComponentType> findAllActive();

    List<ComponentType> findByTypeNameIgnoreCase(String typeName);
}
