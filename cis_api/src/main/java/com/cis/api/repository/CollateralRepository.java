package com.cis.api.repository;

import com.cis.api.model.Collateral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollateralRepository extends JpaRepository<Collateral, Long> {
    
}
