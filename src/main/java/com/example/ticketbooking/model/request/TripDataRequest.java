package com.example.ticketbooking.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class TripDataRequest implements Serializable {
    private String routeId;
    private String date;
}
