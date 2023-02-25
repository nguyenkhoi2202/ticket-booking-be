package com.example.ticketbooking.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class TicketGetByTicketIdResponse implements Serializable {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String date;
    private String time;
    private String vehicleId;
    private String routeId;
    private String stationId;
}
