package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.Vehicle;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Vehicle} entity
 */
@Data
public class VehicleUpdateRequest implements Serializable {
    private final String vehicalId;
    private final String licensePlates;
    private final String color;
    private final String seat;
    private final String vehicleTypeId;
    private final String status;
}