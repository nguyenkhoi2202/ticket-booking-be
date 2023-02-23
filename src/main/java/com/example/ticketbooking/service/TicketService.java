package com.example.ticketbooking.service;

import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;

public interface TicketService {
    CommonResponse createTicket(TicketCreateRequest request);
}
