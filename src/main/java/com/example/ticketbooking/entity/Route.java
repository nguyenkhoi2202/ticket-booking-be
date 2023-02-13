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

    @Column(name = "from", nullable = false)
    private String from;

    @Column(name = "to", nullable = false)
    private String to;

    @Column(name = "travel_time", nullable = false)
    private String travelTime;

    @Column(name = "distance", nullable = false)
    private String distance;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "fare", nullable = false)
    private String fare;

    @Column(name = "station_start_id", nullable = false)
    private String stationStartId;

    @Column(name = "station_end_id", nullable = false)
    private String stationEndId;

    @Column(name = "status", nullable = false)
    private String status;


}
