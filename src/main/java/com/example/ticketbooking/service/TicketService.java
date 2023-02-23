package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.Ticket;
import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;

import java.util.List;

public interface TicketService {
    CommonResponse createTicket(TicketCreateRequest request);

    List<Ticket> getListTicketByUserId(String userId);
}
