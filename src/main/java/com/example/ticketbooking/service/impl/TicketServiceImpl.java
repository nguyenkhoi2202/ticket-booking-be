package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.Ticket;
import com.example.ticketbooking.entity.Trip;
import com.example.ticketbooking.entity.User;
import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.CreateTicketResultResponse;
import com.example.ticketbooking.model.response.TicketGetByTicketIdResponse;
import com.example.ticketbooking.repository.TicketRepository;
import com.example.ticketbooking.repository.TripRepository;
import com.example.ticketbooking.repository.UserRepository;
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
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public TicketGetByTicketIdResponse getTicketByTicketId(String ticketId) {
        TicketGetByTicketIdResponse response = new TicketGetByTicketIdResponse();
        try{
            Ticket ticket = ticketRepository.getByTicketId(ticketId);
            if (ticket != null){
                Trip trip = tripRepository.findById(ticket.getTripId()).get();
                User user = userRepository.findById(ticket.getUserId()).get();
                    response.setFullName(user.getFullname());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setEmail(user.getEmail());
                    response.setDate(String.valueOf(trip.getDate()));
                    response.setTime(trip.getTime());
                    response.setVehicleId(trip.getVehicalId());
                    response.setStationId(trip.getStationId());
                    response.setRouteId(trip.getRouteId());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return response;
        }
    }
}
