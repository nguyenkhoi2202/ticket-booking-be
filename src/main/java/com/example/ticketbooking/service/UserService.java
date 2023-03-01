package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.User;
import com.example.ticketbooking.model.request.UserChangePasswordRequest;
import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.request.UserRegisterRequest;
import com.example.ticketbooking.model.request.UserUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;

import java.util.List;

public interface UserService {
    public CommonResponse loginUser(UserLoginRequest userDto);
    public CommonResponse registerUser(UserRegisterRequest userRegisterRequest);
    public List<UserLoginResponse> getAllUser();
    public CommonResponse updateUser(UserUpdateRequest request);

    CommonResponse deleteUser(String phoneNumber);

    List<UserLoginResponse> searchByPhoneNumber(String keySearch);

    void verify(String code);

    CommonResponse createAdmin(UserRegisterRequest request);

    CommonResponse changePassword(UserChangePasswordRequest request);
}
