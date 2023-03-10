package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.request.RouteUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.RouteDataResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;
import com.example.ticketbooking.repository.RouteRepository;
import com.example.ticketbooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoute() {
        List<Route> routeList = routeRepository.getAllRoute();
        if (routeList != null){
            List<Route> responseList = new ArrayList<>();
            for (int i = 0; i < routeList.size(); i++){
               if (routeList.get(i).getStatus().equals("active")){
                   responseList.add(routeList.get(i));
               }
            }
            return  responseList;
        }else {
            return null;
        }

    }

    @Override
    public CommonResponse createRoute(RouteCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            Route route = new Route();
            route.setRouteId("ro" + request.getFrom().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            route.setFrom(request.getFrom());
            route.setArrive(request.getArrive());
            route.setTravelTime(request.getTravelTime());
            route.setDistance(request.getDistance());
            route.setImage(request.getImage());
            route.setFare(request.getFare());
            route.setStatus("active"); // active
            routeRepository.save(route);
            response.setStatus(200);
            response.setMessage("T???o m???i route th??nh c??ng");
        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("T???o m???i route th???t b???i");
            e.printStackTrace();
        }finally {
            return response;
        }

    }

    @Override
    public CommonResponse updateRoute(RouteUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Route route = routeRepository.findById(request.getRouteId()).get();
            if(route != null){
                route.setFrom(request.getFrom());
                route.setArrive(request.getArrive());
                route.setTravelTime(request.getTravelTime());
                route.setDistance(request.getDistance());
                route.setFare(request.getFare());
                route.setImage(request.getImage());
                route.setStatus(request.getStatus());
                routeRepository.save(route);
                response.setStatus(200);
                response.setMessage("C???p nh???t th??nh c??ng");

            }else {
                response.setStatus(417);
                response.setMessage("Kh??ng t???n t???i route ????? c???p nh???t");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("C???p nh???t th???t b???i");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse deleteRoute(String routeId) {
        CommonResponse response = new CommonResponse();
        try{
            Route route = routeRepository.findById(routeId).get();
            if (route != null){
                route.setStatus("inactive");
                routeRepository.save(route);
                response.setStatus(200);
                response.setMessage("X??a th??nh c??ng");
            }else {
                response.setStatus(417);
                response.setMessage("Kh??ng t???n t???i route ????? x??a");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("X??a th???t b???i");
            e.printStackTrace();
        }finally {
            return  response;
        }
    }

    @Override
    public RouteDataResponse getRouteById(String routeId) {
        RouteDataResponse response = new RouteDataResponse();
        try {
            Route route = routeRepository.findById(routeId).get();
            if (route != null && route.getStatus().equals("active")){
                response.setRouteId(route.getRouteId());
                response.setFrom(route.getFrom());
                response.setArrive(route.getArrive());
                response.setFare(route.getFare());
                response.setImage(route.getImage());
                response.setDistance(route.getDistance());
                response.setTravelTime(route.getTravelTime());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return response;
        }

    }
}
