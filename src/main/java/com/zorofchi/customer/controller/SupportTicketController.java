package com.zorofchi.customer.controller;

import com.zorofchi.customer.entity.SupportTicket;
import com.zorofchi.customer.service.SupportTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class SupportTicketController {

    private final SupportTicketService ticketService;

    public SupportTicketController(SupportTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public SupportTicket createTicket(
            @RequestParam Long customerId,
            @RequestParam String subject,
            @RequestParam String message) {

        return ticketService.createTicket(customerId, subject, message);
    }

    @GetMapping
    public List<SupportTicket> getAllTickets() {
        return ticketService.getAllTickets();
    }


}