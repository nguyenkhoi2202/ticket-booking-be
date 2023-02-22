package com.example.ticketbooking.controller;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.entity.Trip;
import com.example.ticketbooking.model.request.*;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.TripDataResponse;
import com.example.ticketbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @CrossOrigin(origins = "*")
    @PostMapping("/getAllTrip")
    public ResponseEntity<?> getAllTrip() {
        ResponseEntity responseEntity = null;
        try{
            List<TripDataResponse> responses = tripService.getAllTrip();
            if (responses != null){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else {
                responseEntity =  ResponseEntity.status(417).body(responses);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createTrip")
    public ResponseEntity<?> createTrip(@RequestBody TripCreateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = tripService.createTrip(request);
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
    @PostMapping("/updateTrip")
    public ResponseEntity<?> updateTrip(@RequestBody TripUpdateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = tripService.updateTrip(request);
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
    @PostMapping("/deleteTrip")
    public ResponseEntity<?> deleteTrip(@RequestParam String tripId) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = tripService.deleteTrip(tripId);
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
    @PostMapping("/getAllTripByDate")
    public ResponseEntity<?> getAllTripByDate(@RequestBody TripDataRequest request) {
        ResponseEntity responseEntity = null;
        try{
            List<TripDataResponse> responses = tripService.getAllTripByDate(request);
            if (responses != null){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else {
                responseEntity =  ResponseEntity.status(417).body(responses);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }
}
