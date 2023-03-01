package com.example.ticketbooking.controller;

import lombok.Data;

@Data
public class TicketDataResponse {
    private String fullName;
    private String phoneNumber;
    private String ticketId;
    private String booking_date;
    private String seatNo;
    private String price;
    private String status;
    private String userId;
    private String tripId;
}
