
package com.zorofchi.customer.controller;

import com.zorofchi.customer.entity.Invoice;
import com.zorofchi.customer.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public Invoice createInvoice(
            @RequestParam Long subscriptionId,
            @RequestParam double amount) {

        return invoiceService.createInvoice(subscriptionId, amount);
    }

    @PutMapping("/{id}/pay")
    public Invoice payInvoice(@PathVariable Long id) {
        return invoiceService.payInvoice(id);
    }

    @PostMapping("/renew")
    public Invoice createRenewalInvoice(
            @RequestParam Long subscriptionId,
            @RequestParam double amount,
            @RequestParam LocalDate newEndDate) {

        return invoiceService.createRenewalInvoice(
                subscriptionId,
                amount,
                newEndDate
        );
    }



}
