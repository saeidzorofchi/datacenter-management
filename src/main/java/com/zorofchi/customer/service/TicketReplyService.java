package com.zorofchi.customer.service;

import com.zorofchi.customer.entity.SupportTicket;
import com.zorofchi.customer.entity.TicketReply;
import com.zorofchi.customer.repository.SupportTicketRepository;
import com.zorofchi.customer.repository.TicketReplyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketReplyService {

    private final TicketReplyRepository replyRepository;
    private final SupportTicketRepository ticketRepository;

    public TicketReplyService(
            TicketReplyRepository replyRepository,
            SupportTicketRepository ticketRepository) {
        this.replyRepository = replyRepository;
        this.ticketRepository = ticketRepository;
    }

    public TicketReply replyToTicket(Long ticketId, String message) {

        SupportTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        TicketReply reply = new TicketReply();
        reply.setTicket(ticket);
        reply.setMessage(message);
        reply.setCreatedAt(LocalDateTime.now());

        return replyRepository.save(reply);
    }

    public List<TicketReply> getRepliesByTicket(Long ticketId) {
        return replyRepository.findByTicket_Id(ticketId);
    }

}