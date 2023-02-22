package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RouteRepository extends JpaRepository<Route, String> {

    @Query(value = "select * from route", nativeQuery = true)
    List<Route> getAllRoute();


    @Query(value = "select * from route where route_id = :routeId ", nativeQuery = true)
    Route getRouteByRouteId(@Param("routeId") String routeId);
}