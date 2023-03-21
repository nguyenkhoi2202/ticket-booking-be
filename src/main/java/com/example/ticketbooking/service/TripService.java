package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.Trip;
import com.example.ticketbooking.model.request.TripCreateRequest;
import com.example.ticketbooking.model.request.TripDataRequest;
import com.example.ticketbooking.model.request.TripUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.TripDataResponse;

import java.text.ParseException;
import java.util.List;

public interface TripService {
    List<TripDataResponse> getAllTrip();

    CommonResponse createTrip(TripCreateRequest request);

    CommonResponse updateTrip(TripUpdateRequest request);

    CommonResponse deleteTrip(String tripId);

    List<TripDataResponse> getAllTripByDate(TripDataRequest request);

    void cancelTicket() throws ParseException;

    Trip getTripByTripId(String tripId);
}
