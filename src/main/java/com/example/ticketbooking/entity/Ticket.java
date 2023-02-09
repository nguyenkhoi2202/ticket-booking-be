package com.example.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @Column(name = "ticket_id", nullable = false)
    private String ticketId;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    @Column(name = "seat_no", nullable = false)
    private String seatNo;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "trip_id", nullable = false)
    private int tripId;


}
