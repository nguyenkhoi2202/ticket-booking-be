package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.Route;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Route} entity
 */
@Data
public class RouteUpdateRequest implements Serializable {
    private final String routeId;
    private final String from;
    private final String arrive;
    private final String travelTime;
    private final String distance;
    private final String image;
    private final String fare;
    private final String status;
}