package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, String> {
}