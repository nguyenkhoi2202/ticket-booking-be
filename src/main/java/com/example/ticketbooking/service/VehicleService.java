package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.Vehicle;
import com.example.ticketbooking.model.request.VehicleCreateRequest;
import com.example.ticketbooking.model.request.VehicleUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicle();

    CommonResponse deleteVehicle(String vehicleId);

    CommonResponse ụpdateVehicle(VehicleUpdateRequest request);

    CommonResponse createVehicle(VehicleCreateRequest request);
}
