package com.example.ticketbooking.controller;


import com.example.ticketbooking.common.CommonClass;
import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.request.RouteUpdateRequest;
import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteService routeService;


    @CrossOrigin(origins = "*")
    @PostMapping("/getAllRoutes")
    public ResponseEntity<?> getAllRoutes() {
        ResponseEntity responseEntity = null;
        try{
            List<Route> responses = routeService.getAllRoute();
            if (responses != null){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createRoute")
    public ResponseEntity<?> createRoute(@RequestBody RouteCreateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = routeService.createRoute(request);
            if (responses.getStatus() == 200){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else{
                responseEntity =  ResponseEntity.status(417).body(responses);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateRoute")
    public ResponseEntity<?> updateRoute(@RequestBody RouteUpdateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = routeService.updateRoute(request);
            if (responses.getStatus() == 200){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else{
                responseEntity =  ResponseEntity.status(417).body(responses);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/deleteRoute")
    public ResponseEntity<?> deleteRoute(@RequestParam String routeId) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = routeService.deleteRoute(routeId);
            if (responses.getStatus() == 200){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else{
                responseEntity =  ResponseEntity.status(417).body(responses);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }
}
