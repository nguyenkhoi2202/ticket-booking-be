package com.example.ticketbooking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketResultResponse implements Serializable {
    private String tripId;
    private String seatNo;
}
