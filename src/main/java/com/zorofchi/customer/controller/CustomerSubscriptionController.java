package com.zorofchi.customer.controller;

import com.zorofchi.customer.entity.CustomerSubscription;
import com.zorofchi.customer.service.CustomerSubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/customer-subscriptions")
public class CustomerSubscriptionController {

    private final CustomerSubscriptionService subscriptionService;

    public CustomerSubscriptionController(CustomerSubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/rent")
    public CustomerSubscription rentServer(
            @RequestParam Long customerId,
            @RequestParam Long equipmentId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return subscriptionService.rentServer(
                customerId,
                equipmentId,
                startDate,
                endDate
        );
    }





}