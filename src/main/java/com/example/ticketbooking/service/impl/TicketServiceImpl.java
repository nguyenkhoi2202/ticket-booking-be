package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.Ticket;
import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.CreateTicketResultResponse;
import com.example.ticketbooking.repository.TicketRepository;
import com.example.ticketbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public CommonResponse createTicket(TicketCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            Ticket ticket = new Ticket();
            int numberTicket = ticketRepository.getNumberTicketByTripId(request.getTripId());

            ticket.setTicketId("ti" + request.getUserId().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            ticket.setBookingDate(format.parse(format.format(new Date())));
            ticket.setPrice(Float.parseFloat(request.getPrice()));
            ticket.setUserId(request.getUserId());
            ticket.setTripId(request.getTripId());
            ticket.setSeatNo("sea" + String.valueOf(numberTicket + 1));
            ticket.setStatus("active");
            ticketRepository.save(ticket);
            response.setStatus(200);
            response.setMessage("Đặt vé thành công");
            response.setData(new CreateTicketResultResponse(request.getTripId(),"sea" + String.valueOf(numberTicket + 1)));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return response;
        }

    }

    @Override
    public List<Ticket> getListTicketByUserId(String userId) {
        List<Ticket> ticketList = new ArrayList<>();
        try{
            ticketList = ticketRepository.getListTicketByUserId(userId);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return ticketList;
        }
    }
}
