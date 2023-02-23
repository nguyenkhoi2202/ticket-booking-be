package com.example.ticketbooking.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class TicketCreateRequest implements Serializable {
    private String price;
    private String userId;
    private String tripId;
}
