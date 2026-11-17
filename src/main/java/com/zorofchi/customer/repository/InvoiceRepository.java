package com.zorofchi.customer.repository;

import com.zorofchi.customer.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice , Long> {
}
