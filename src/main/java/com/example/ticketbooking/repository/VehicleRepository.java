package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    @Query(value = "select * from vehicle", nativeQuery = true)
    List<Vehicle> getAllVehicle();

    Vehicle findVehicleByLicensePlates(String licencePlates);
    @Query(value = "select * from vehicle where vehical_id = :vehicleId", nativeQuery = true)
    Vehicle getVehicleById(@Param("vehicleId") String vehicalId);

}