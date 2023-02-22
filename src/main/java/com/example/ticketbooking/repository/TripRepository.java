package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, String> {

    @Query(value = "select  * from trips", nativeQuery = true)
    List<Trip> getAllTrip();

    @Query(value = "select * from trips where route_id = :routeId and date = :date ",nativeQuery = true)
    List<Trip> getListTripByRouteIdAndDate(@Param("routeId") String routeId, @Param("date") Date date);
}