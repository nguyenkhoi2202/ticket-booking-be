package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}