package com.example.ticketbooking.service;

import com.example.ticketbooking.controller.TicketDataResponse;
import com.example.ticketbooking.entity.Ticket;
import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.TicketGetByTicketIdResponse;

import java.util.List;

public interface TicketService {
    CommonResponse createTicket(TicketCreateRequest request);

    List<Ticket> getListTicketByUserId(String userId);

    TicketGetByTicketIdResponse getTicketByTicketId(String ticketId);

    CommonResponse deleteTicket(String ticketId);

    List<TicketDataResponse> getAllTicket();
}
