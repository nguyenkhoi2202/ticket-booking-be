package com.example.ticketbooking.controller;

import com.example.ticketbooking.entity.FeedBack;
import com.example.ticketbooking.entity.Trip;
import com.example.ticketbooking.model.request.FeedBackCreateRequest;
import com.example.ticketbooking.model.request.FeedBackUpdateRequest;
import com.example.ticketbooking.model.request.TripCreateRequest;
import com.example.ticketbooking.model.request.TripUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;


    @CrossOrigin(origins = "*")
    @PostMapping("/getAllFeedBack")
    public ResponseEntity<?> getAllFeedBack() {
        ResponseEntity responseEntity = null;
        try{
            List<FeedBack> responses = feedbackService.getAllFeedBack();
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
    @PostMapping("/createFeedBack")
    public ResponseEntity<?> createFeedBack(@RequestBody FeedBackCreateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = feedbackService.createFeedBack(request);
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
    @PostMapping("/updateFeedBack")
    public ResponseEntity<?> updateFeedBack(@RequestBody FeedBackUpdateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = feedbackService.updateFeedBack(request);
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
    @PostMapping("/deleteFeedBack")
    public ResponseEntity<?> deleteTrip(@RequestParam String feedBackId) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = feedbackService.deleteFeedBack(feedBackId);
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
