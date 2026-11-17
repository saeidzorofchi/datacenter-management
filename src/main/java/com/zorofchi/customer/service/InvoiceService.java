
package com.zorofchi.customer.service;

import com.zorofchi.customer.entity.Invoice;
import com.zorofchi.customer.entity.CustomerSubscription;
import com.zorofchi.customer.repository.InvoiceRepository;
import com.zorofchi.customer.repository.CustomerSubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerSubscriptionRepository subscriptionRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            CustomerSubscriptionRepository subscriptionRepository) {
        this.invoiceRepository = invoiceRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Invoice createInvoice(Long subscriptionId, double amount) {

        CustomerSubscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        Invoice invoice = new Invoice();
        invoice.setCustomer(subscription.getCustomer());
        invoice.setSubscription(subscription);
        invoice.setAmount(amount);
        invoice.setIssueDate(LocalDate.now());
        invoice.setStatus("UNPAID");

        return invoiceRepository.save(invoice);
    }

    public Invoice payInvoice(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        invoice.setStatus("PAID");

        if (invoice.getRenewalEndDate() != null) {
            CustomerSubscription subscription = invoice.getSubscription();

            subscription.setEndDate(invoice.getRenewalEndDate());

            subscriptionRepository.save(subscription);
        }

        return invoiceRepository.save(invoice);
    }


    public Invoice createRenewalInvoice(Long subscriptionId, double amount, LocalDate newEndDate) {

        CustomerSubscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        if (!newEndDate.isAfter(subscription.getEndDate())) {
            throw new RuntimeException("New end date must be after current end date");
        }

        Invoice invoice = new Invoice();
        invoice.setCustomer(subscription.getCustomer());
        invoice.setSubscription(subscription);
        invoice.setAmount(amount);
        invoice.setIssueDate(LocalDate.now());
        invoice.setStatus("UNPAID");
        invoice.setRenewalEndDate(newEndDate);

        return invoiceRepository.save(invoice);
    }


}
