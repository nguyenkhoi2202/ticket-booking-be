package com.example.ticketbooking.controller;


import com.example.ticketbooking.entity.Station;
import com.example.ticketbooking.model.request.StationCreateRequest;
import com.example.ticketbooking.model.request.StationUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    StationService stationService;


    @CrossOrigin(origins = "*")
    @PostMapping("/getAllStation")
    public ResponseEntity<?> getAllStation() {
        ResponseEntity responseEntity = null;
        try{
            List<Station> responses = stationService.getAllStation();
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
    @PostMapping("/createStation")
    public ResponseEntity<?> createStation(@RequestBody StationCreateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = stationService.createStation(request);
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
    @PostMapping("/updateStation")
    public ResponseEntity<?> updateStation(@RequestBody StationUpdateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = stationService.updateStation(request);
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
    @PostMapping("/deleteStation")
    public ResponseEntity<?> deleteStation(@RequestParam String stationId) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = stationService.deleteStation(stationId);
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
