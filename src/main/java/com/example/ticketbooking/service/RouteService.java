package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.request.RouteUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.RouteDataResponse;

import java.util.List;

public interface RouteService {
    List<Route> getAllRoute();

    CommonResponse createRoute(RouteCreateRequest request);

    CommonResponse updateRoute(RouteUpdateRequest request);

    CommonResponse deleteRoute(String routeId);

    RouteDataResponse getRouteById(String routeId);
}
