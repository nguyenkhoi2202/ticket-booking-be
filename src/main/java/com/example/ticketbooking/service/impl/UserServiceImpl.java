package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.common.CommonClass;
import com.example.ticketbooking.entity.User;
import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.request.UserRegisterRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;
import com.example.ticketbooking.repository.UserRepository;
import com.example.ticketbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CommonResponse loginUser(UserLoginRequest userDto) {
        CommonResponse response = new CommonResponse();
        try{
            User user = userRepository.findByPhoneNumber(userDto.getPhoneNumber().trim());
            String passwordEncrypt = CommonClass.getMD5(userDto.getPassword().trim());
            if (user == null){
                response.setStatus(417);
                response.setMessage("Tài khoản không tồn tại !!!");
            }else {
                if (passwordEncrypt.equals(user.getPassword().trim()) && user.getStatus().equals("Active")){
                    response.setStatus(200);
                    response.setMessage("Login thành công !!!");
                    UserLoginResponse loginResponse = new UserLoginResponse();
                    loginResponse.setPhoneNumber(user.getPhoneNumber());
                    loginResponse.setFullname(user.getFullname());
                    loginResponse.setEmail(user.getEmail());
                    loginResponse.setGender(user.getGender());
                    loginResponse.setRole(user.getRole());
                    response.setData(loginResponse);
                } else if (passwordEncrypt.equals(user.getPassword().trim()) && user.getStatus().equals("InActive")) {
                    response.setStatus(417);
                    response.setMessage("Tài khoản bạn đang bị tạm khóa !!!");
                } else if (!passwordEncrypt.equals(user.getPassword().trim())) {
                    response.setStatus(417);
                    response.setMessage("Mật khẩu sai !!!");
                }
            }

        }catch (Exception e){

            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse registerUser(UserRegisterRequest userRegisterRequest) {
        CommonResponse response = new CommonResponse();
        try{
            User checkExist = userRepository.findByPhoneNumber(userRegisterRequest.getPhoneNumber().trim());
            if (checkExist == null){
                User user = new User();
                Random random = new Random();
                user.setUserId(userRegisterRequest.getPhoneNumber().substring(5).concat(Integer.toString(random.nextInt(99))));
                user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
                user.setPassword(CommonClass.getMD5(userRegisterRequest.getPassword().trim()));
                user.setEmail(userRegisterRequest.getEmail());
                user.setFullname(userRegisterRequest.getFullname());
                user.setGender(userRegisterRequest.getGender());
                user.setRole("user");
                user.setStatus("Active");
                userRepository.save(user);
                response.setStatus(200);
                response.setMessage("Đăng kí tài khỏan mới thành công !!!");
            }else {
                response.setStatus(417);
                response.setMessage("Tài khoản đã tồn tại !!!");
            }


        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Đăng kí tài khỏan mới thất bại !!!");
            e.printStackTrace();
        }finally {
            return response;
        }

    }
}
