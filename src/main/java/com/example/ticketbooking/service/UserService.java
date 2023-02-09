package com.example.ticketbooking.service;

import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.request.UserRegisterRequest;
import com.example.ticketbooking.model.response.CommonResponse;

public interface UserService {
    public CommonResponse loginUser(UserLoginRequest userDto);
    public CommonResponse registerUser(UserRegisterRequest userRegisterRequest);
}
