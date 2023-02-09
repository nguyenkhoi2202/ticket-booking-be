package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, String> {
}