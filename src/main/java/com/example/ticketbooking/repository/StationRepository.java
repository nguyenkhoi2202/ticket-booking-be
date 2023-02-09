package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, String> {
}