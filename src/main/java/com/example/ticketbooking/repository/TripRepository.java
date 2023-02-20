package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, String> {

    @Query(value = "select  * from trips", nativeQuery = true)
    List<Trip> getAllTrip();
}