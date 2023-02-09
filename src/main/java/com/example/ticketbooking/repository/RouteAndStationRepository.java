package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.RouteAndStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteAndStationRepository extends JpaRepository<RouteAndStation, Long> {
}