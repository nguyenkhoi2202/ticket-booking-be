package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.Station;
import com.example.ticketbooking.model.request.StationCreateRequest;
import com.example.ticketbooking.model.request.StationUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;

import java.util.List;

public interface StationService {
    List<Station> getAllStation();

    CommonResponse createStation(StationCreateRequest request);

    CommonResponse updateStation(StationUpdateRequest request);

    CommonResponse deleteStation(String stationId);
}
