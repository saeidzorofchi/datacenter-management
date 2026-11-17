package com.zorofchi.customer.controller;

import com.zorofchi.customer.entity.TicketReply;
import com.zorofchi.customer.service.TicketReplyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-replies")
public class TicketReplyController {

    private final TicketReplyService replyService;

    public TicketReplyController(TicketReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    public TicketReply replyToTicket(
            @RequestParam Long ticketId,
            @RequestParam String message) {

        return replyService.replyToTicket(ticketId, message);
    }

    @GetMapping
    public List<TicketReply> getRepliesByTicket(@RequestParam Long ticketId) {
        return replyService.getRepliesByTicket(ticketId);
    }

}