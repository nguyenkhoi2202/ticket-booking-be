package com.example.ticketbooking.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @Column(name = "route_id", nullable = false)
    private String routeId;

    @Column(name = "froms")
    private String from;

    @Column(name = "arrive")
    private String arrive;

    @Column(name = "travel_time")
    private String travelTime;

    @Column(name = "distance")
    private String distance;

    @Column(name = "image")
    private String image;

    @Column(name = "fare")
    private String fare;

    @Column(name = "status", nullable = false)
    private String status;


}
