package com.example.ticketbooking.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route_and_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteAndStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "route_id", nullable = false)
    private String routeId;

    @Column(name = "station_id", nullable = false)
    private String stationId;
}
