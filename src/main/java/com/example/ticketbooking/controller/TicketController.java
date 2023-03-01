package com.example.ticketbooking.controller;

import com.example.ticketbooking.entity.Ticket;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.TicketGetByTicketIdResponse;
import com.example.ticketbooking.model.response.TripDataResponse;
import com.example.ticketbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @CrossOrigin(origins = "*")
    @PostMapping("/createTicket")
    public ResponseEntity<?> createRoute(@RequestBody TicketCreateRequest request) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = ticketService.createTicket(request);
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
    @PostMapping("/getListTicketByUserId")
    public ResponseEntity<?> getListTicketByUserId(@RequestParam String userId) {
        ResponseEntity responseEntity = null;
        try{
            List<Ticket> responses = ticketService.getListTicketByUserId(userId);
            if (responses.size() != 0){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else{
                responseEntity =  ResponseEntity.status(417).body("Không có ticket nào");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getTicketByTicketId")
    public ResponseEntity<?> getTicketByTicketId(@RequestParam String ticketId) {
        ResponseEntity responseEntity = null;
        try{
            TicketGetByTicketIdResponse responses = ticketService.getTicketByTicketId(ticketId);
            if (responses.getPhoneNumber() != null){
                responseEntity =  ResponseEntity.status(200).body(responses);
            }else{
                responseEntity =  ResponseEntity.status(417).body("Không có ticket nào");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseEntity;
        }
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/deleteTicket")
    public ResponseEntity<?> deleteRoute(@RequestParam String ticketId) {
        ResponseEntity responseEntity = null;
        try{
            CommonResponse responses = ticketService.deleteTicket(ticketId);
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
    @PostMapping("/getAllTicket")
    public ResponseEntity<?> getAllTicket() {
        ResponseEntity responseEntity = null;
        try{
            List<TicketDataResponse> responses = ticketService.getAllTicket();
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
