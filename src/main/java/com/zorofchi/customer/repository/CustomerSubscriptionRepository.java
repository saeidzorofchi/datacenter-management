package com.zorofchi.customer.repository;

import com.zorofchi.customer.entity.CustomerSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CustomerSubscriptionRepository
        extends JpaRepository<CustomerSubscription, Long> {

    boolean existsByEquipment_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long equipmentId,
            LocalDate endDate,
            LocalDate startDate
    );
}