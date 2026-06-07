package com.cis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cis.api.entity.DeliverableBillingDetails;

public interface DeliverableBillingDetailsRepository extends JpaRepository<DeliverableBillingDetails, Integer> {
    DeliverableBillingDetails findByDeliverableScheduleDetailsId(Integer id);
}
