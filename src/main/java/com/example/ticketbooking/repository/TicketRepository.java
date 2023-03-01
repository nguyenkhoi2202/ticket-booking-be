package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(value = "select count(*) from ticket where trip_id = :tripId",nativeQuery = true)
    int getNumberTicketByTripId(@Param("tripId") String tripId);

    @Query(value = "select * from ticket where user_id = :userId",nativeQuery = true)
    List<Ticket> getListTicketByUserId(@Param("userId") String userId);

    @Query(value = "select * from ticket where ticket_id = :ticketId", nativeQuery = true)
    Ticket getByTicketId(@Param("ticketId") String ticketId);

    @Query(value = "select * from ticket", nativeQuery = true)
    List<Ticket> getAllTicket();
}