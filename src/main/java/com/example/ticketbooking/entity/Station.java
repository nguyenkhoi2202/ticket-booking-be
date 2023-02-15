package com.example.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @Column(name = "station_id", nullable = false)
    private String stationId;

    @Column(name = "station_start", nullable = false)
    private String stationStart;

    @Column(name = "station_end", nullable = false)
    private String stationEnd;

    @Column(name = "status", nullable = false)
    private String status;

}
