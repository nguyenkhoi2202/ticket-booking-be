package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link Route} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteCreateRequest implements Serializable {
    private  String from;
    private  String arrive;
    private  String travelTime;
    private  String distance;
    private  String image;
    private  String fare;
}