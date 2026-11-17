package com.zorofchi.customer.repository;

import com.zorofchi.customer.entity.TicketReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketReplyRepository extends JpaRepository<TicketReply, Long> {
    List<TicketReply> findByTicket_Id(Long ticketId);
}