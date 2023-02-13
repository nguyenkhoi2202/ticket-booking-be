package com.example.ticketbooking.controller;

import com.example.ticketbooking.common.CommonClass;
import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.request.UserRegisterRequest;
import com.example.ticketbooking.model.request.UserUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;
import com.example.ticketbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        ResponseEntity responseEntity = null;
        try{
            if (request.getPhoneNumber().isEmpty() || request.getPassword().isEmpty()){
                responseEntity = ResponseEntity.status(417).body(CommonClass.notEmpty());
            }else {
                CommonResponse response = userService.loginUser(request);
                if (response.getStatus() == 200){
                    responseEntity = ResponseEntity.status(200).body(response);
                } else if (response.getStatus() == 417) {
                    responseEntity = ResponseEntity.status(417).body(response);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        ResponseEntity responseEntity = null;
        try{
            if (request.getPhoneNumber().isEmpty() || request.getPassword().isEmpty() ||
                    request.getEmail().isEmpty() || request.getFullname().isEmpty() || request.getGender().isEmpty()){
                responseEntity = ResponseEntity.status(417).body(CommonClass.notEmpty());
            }else {
                CommonResponse response = userService.registerUser(request);
                if (response.getStatus() == 200){
                    responseEntity =  ResponseEntity.status(200).body(response);
                } else if (response.getStatus() == 417) {
                    responseEntity = ResponseEntity.status(417).body(response);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        ResponseEntity responseEntity = null;
        try{
            List<UserLoginResponse> responseList = userService.getAllUser();
            if (responseList != null){
                responseEntity =  ResponseEntity.status(200).body(responseList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse response = userService.updateUser(request);
            if (response.getStatus() == 200){
                responseEntity =  ResponseEntity.status(200).body(response);
            }else if(response.getStatus() == 417) {
                responseEntity =  ResponseEntity.status(417).body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam String phoneNumber) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse response = userService.deleteUser(phoneNumber);
            if (response.getStatus() == 200){
                responseEntity =  ResponseEntity.status(200).body(response);
            }else if(response.getStatus() == 417) {
                responseEntity =  ResponseEntity.status(417).body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/searchByPhoneNumber")
    public ResponseEntity<?> searchByPhoneNumber(@RequestParam String keySearch) {
        ResponseEntity responseEntity = null;
        try{
            List<UserLoginResponse> response = userService.searchByPhoneNumber(keySearch);
            if (response != null){
                responseEntity =  ResponseEntity.status(200).body(response);
            }else {
                responseEntity =  ResponseEntity.status(417).body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }

    }
}
