package com.zorofchi.customer.service;

import com.zorofchi.customer.entity.Customer;
import com.zorofchi.customer.entity.CustomerSubscription;
import com.zorofchi.customer.repository.CustomerRepository;
import com.zorofchi.datacenter.entity.Equipment;

import java.time.LocalDate;
import com.zorofchi.customer.repository.CustomerSubscriptionRepository;
import com.zorofchi.datacenter.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerSubscriptionService {

    private final CustomerSubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final EquipmentRepository equipmentRepository;

    public CustomerSubscriptionService(CustomerSubscriptionRepository subscriptionRepository, CustomerRepository customerRepository, EquipmentRepository equipmentRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.equipmentRepository = equipmentRepository;
    }



    public CustomerSubscription rentServer(
            Long customerId,
            Long equipmentId,
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate == null || endDate == null || !endDate.isAfter(startDate)) {
            throw new RuntimeException("Invalid rental dates");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        if (!"SERVER".equals(equipment.getType())) {
            throw new RuntimeException("Equipment is not a server");
        }

        boolean alreadyRented =
                subscriptionRepository
                        .existsByEquipment_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                                equipmentId,
                                endDate,
                                startDate
                        );

        if (alreadyRented) {
            throw new RuntimeException("Server is already rented during this period");
        }

        CustomerSubscription subscription = new CustomerSubscription();
        subscription.setCustomer(customer);
        subscription.setEquipment(equipment);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        return subscriptionRepository.save(subscription);
    }



}