package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(value = "select count(*) from ticket where trip_id = :tripId",nativeQuery = true)
    int getNumberTicketByTripId(@Param("tripId") String tripId);
}