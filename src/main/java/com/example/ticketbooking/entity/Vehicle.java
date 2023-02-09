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

    @Column(name = "license_plates", nullable = false)
    private String licensePlates;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "seat", nullable = false)
    private String seat;

    @Column(name = "vehicle_type_id", nullable = false)
    private String vehicleTypeId;

    @Column(name = "status", nullable = false)
    private String status;

}
