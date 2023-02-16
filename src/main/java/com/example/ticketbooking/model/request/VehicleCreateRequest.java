package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.Vehicle;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Vehicle} entity
 */
@Data
public class VehicleCreateRequest implements Serializable {
    private final String licensePlates;
    private final String color;
    private final String seat;
    private final String vehicleTypeId;
}