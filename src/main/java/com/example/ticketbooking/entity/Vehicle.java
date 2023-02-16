package com.example.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @Column(name = "vehical_id", nullable = false)
    private String vehicalId;

    @Column(name = "license_plates", unique = true)
    private String licensePlates;

    @Column(name = "color")
    private String color;

    @Column(name = "seat")
    private String seat;

    @Column(name = "vehicle_type_id", nullable = false)
    private String vehicleTypeId;

    @Column(name = "status", nullable = false)
    private String status;

}
