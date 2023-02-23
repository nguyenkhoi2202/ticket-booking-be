package com.example.ticketbooking.controller;

import com.example.ticketbooking.mail.EmailSenderService;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class SendMailtest {

    @Autowired
    EmailSenderService emailSenderService;

    @CrossOrigin(origins = "*")
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail() {
        ResponseEntity responseEntity = null;
        try{
            emailSenderService.sendSimpleEmail("khoitn2202@gmail.com", "ahihi", "noi dung ne");
            responseEntity = ResponseEntity.status(200).body("ok");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }
}
