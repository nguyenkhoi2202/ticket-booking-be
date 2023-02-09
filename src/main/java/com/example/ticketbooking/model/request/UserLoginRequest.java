package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserLoginRequest implements Serializable {
    private final String password;
    private final String phoneNumber;
}