package com.example.ticketbooking.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateRequest implements Serializable {
    private final String fullname;
    private final String gender;
    private final String phoneNumber;
    private final String email;
    private final String status;
}
