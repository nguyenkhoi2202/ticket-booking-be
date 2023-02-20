package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.Station;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Station} entity
 */
@Data
public class StationCreateRequest implements Serializable {
    private final String stationStart;
    private final String stationEnd;
}