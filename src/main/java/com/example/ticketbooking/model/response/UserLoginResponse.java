package com.example.ticketbooking.model.response;

import com.example.ticketbooking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse implements Serializable {
    private  String userId;
    private  String fullname;
    private  String gender;
    private  String phoneNumber;
    private  String email;
    private  String role;
}