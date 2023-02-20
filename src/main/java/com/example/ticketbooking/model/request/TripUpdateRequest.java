package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.Trip;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Trip} entity
 */
@Data
public class TripUpdateRequest implements Serializable {
    private final String tripId;
    private final String date;
    private final String time;
    private final String status;
}