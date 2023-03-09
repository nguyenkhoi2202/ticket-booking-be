package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, String> {

    @Query(value = "select  * from trips", nativeQuery = true)
    List<Trip> getAllTrip();

    @Query(value = "select * from trips where route_id = :routeId and date_trip = :date ",nativeQuery = true)
    List<Trip> getListTripByRouteIdAndDate(@Param("routeId") String routeId, @Param("date") Date date);

//    @Query(value = "select vehical_id from trips where trip_id = :tripId",nativeQuery = true)
//    String getVehicleIdBytripId(@Param("tripId") String tripId);


    @Query(value = "select trip_id from trips where date_trip = :dateTrip ",nativeQuery = true)
    List<String>  getAllDate(@Param("dateTrip") LocalDate dateTrip);

    @Query(value = "select * from trips where trip_id = :tripId", nativeQuery = true)
    Trip getTripByTripId(@Param("tripId") String tripId);



}