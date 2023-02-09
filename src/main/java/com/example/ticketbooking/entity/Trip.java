package com.example.ticketbooking.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "trip")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @Column(name = "trip_id", nullable = false)
    private String tripId;

    @Column(name = "vehical_id", nullable = false)
    private String vehicalId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "route_id", nullable = false)
    private String routeId;

    @Column(name = "status", nullable = false)
    private String status;


}
