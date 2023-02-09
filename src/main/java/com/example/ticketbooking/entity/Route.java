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

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @Column(name = "station_start_id", nullable = false)
    private String stationStartId;

    @Column(name = "station_end_id", nullable = false)
    private String stationEndId;

    @Column(name = "total_seat", nullable = false)
    private String totalSeat;

    @Column(name = "status", nullable = false)
    private String status;


}
