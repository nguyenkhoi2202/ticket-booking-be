package com.example.ticketbooking.controller;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.entity.Vehicle;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.request.RouteUpdateRequest;
import com.example.ticketbooking.model.request.VehicleCreateRequest;
import com.example.ticketbooking.model.request.VehicleUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @CrossOrigin(origins = "*")
    @PostMapping("/getAllVehicle")
    public ResponseEntity<?> getAllVehicle() {
        ResponseEntity responseEntity = null;
        try{
            List<Vehicle> responses = vehicleService.getAllVehicle();
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
    @PostMapping("/deleteVehicle")
    public ResponseEntity<?> deleteVehicle(@RequestParam String vehicleId) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = vehicleService.deleteVehicle(vehicleId);
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
    @PostMapping("/updateVehicle")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleUpdateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = vehicleService.ụpdateVehicle(request);
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
    @PostMapping("/createVehicle")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleCreateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = vehicleService.createVehicle(request);
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
    @PostMapping("/getVehicleById")
    public ResponseEntity<?> getVehicleById(@RequestParam String vehicleId) {
        ResponseEntity responseEntity = null;
        try{
            Vehicle responses = vehicleService.getVehicleById(vehicleId);
            if (responses != null){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else {
                responseEntity =  ResponseEntity.status(417).body("Không tìm thấy vehicle !!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }
}
