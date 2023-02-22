package com.example.ticketbooking.model.response;

import com.example.ticketbooking.entity.Route;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Route} entity
 */
@Data
public class RouteDataResponse implements Serializable {
    private  String routeId;
    private  String from;
    private  String arrive;
    private  String travelTime;
    private  String distance;
    private  String image;
    private  String fare;
}