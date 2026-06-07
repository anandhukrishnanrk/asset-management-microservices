package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.PointsofContact;
import com.cis.api.entity.TechDetails;

public interface TechdetailsRepository extends JpaRepository<TechDetails, Long> {

    }