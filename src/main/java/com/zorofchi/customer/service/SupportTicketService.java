package com.zorofchi.customer.service;

import com.zorofchi.customer.entity.TicketStatus;
import com.zorofchi.customer.entity.Customer;
import com.zorofchi.customer.entity.SupportTicket;
import com.zorofchi.customer.repository.CustomerRepository;
import com.zorofchi.customer.repository.SupportTicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupportTicketService {

    private final SupportTicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    public SupportTicketService(
            SupportTicketRepository ticketRepository,
            CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
    }

    public SupportTicket createTicket(Long customerId, String subject, String message) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        SupportTicket ticket = new SupportTicket();
        ticket.setCustomer(customer);
        ticket.setSubject(subject);
        ticket.setMessage(message);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<SupportTicket> getAllTickets() {
        return ticketRepository.findAll();
    }


}