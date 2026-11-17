
package com.zorofchi.customer.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

// Represents an invoice for a customer's service purchase or renewal

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private LocalDate issueDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private CustomerSubscription subscription;

    private LocalDate renewalEndDate;



    public LocalDate getRenewalEndDate() {
        return renewalEndDate;
    }

    public void setRenewalEndDate(LocalDate renewalEndDate) {
        this.renewalEndDate = renewalEndDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(CustomerSubscription subscription) {
        this.subscription = subscription;
    }
}
