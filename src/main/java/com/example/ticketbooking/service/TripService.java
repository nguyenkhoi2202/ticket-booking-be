package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.Trip;
import com.example.ticketbooking.model.request.TripCreateRequest;
import com.example.ticketbooking.model.request.TripUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;

import java.util.List;

public interface TripService {
    List<Trip> getAllTrip();

    CommonResponse createTrip(TripCreateRequest request);

    CommonResponse updateTrip(TripUpdateRequest request);

    CommonResponse deleteTrip(String tripId);
}
