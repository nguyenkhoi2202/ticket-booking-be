package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.common.CommonClass;
import com.example.ticketbooking.entity.User;
import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.request.UserRegisterRequest;
import com.example.ticketbooking.model.request.UserUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;
import com.example.ticketbooking.repository.UserRepository;
import com.example.ticketbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                if (passwordEncrypt.equals(user.getPassword().trim()) && user.getStatus().equals("active")){
                    response.setStatus(200);
                    response.setMessage("Login thành công !!!");
                    UserLoginResponse loginResponse = new UserLoginResponse();
                    loginResponse.setPhoneNumber(user.getPhoneNumber());
                    loginResponse.setFullname(user.getFullname());
                    loginResponse.setEmail(user.getEmail());
                    loginResponse.setGender(user.getGender());
                    loginResponse.setRole(user.getRole());
                    response.setData(loginResponse);
                } else if (passwordEncrypt.equals(user.getPassword().trim()) && user.getStatus().equals("inactive")) {
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
                user.setUserId("user" + userRegisterRequest.getPhoneNumber().substring(5).concat(Integer.toString(random.nextInt(99))));
                user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
                user.setPassword(CommonClass.getMD5(userRegisterRequest.getPassword().trim()));
                user.setEmail(userRegisterRequest.getEmail());
                user.setFullname(userRegisterRequest.getFullname());
                user.setGender(userRegisterRequest.getGender());
                user.setRole("user");
                user.setStatus("active");
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

    @Override
    public List<UserLoginResponse> getAllUser() {
        List<User> userList = userRepository.getAllUser();
        if (userList != null){
            List<UserLoginResponse> responseList = new ArrayList<>();
            for (int i = 0; i < userList.size(); i++){
               if (userList.get(i).getStatus().equals("active")){
                   UserLoginResponse userLoginResponse = new UserLoginResponse();
                   userLoginResponse.setPhoneNumber(userList.get(i).getPhoneNumber());
                   userLoginResponse.setFullname(userList.get(i).getFullname());
                   userLoginResponse.setEmail(userList.get(i).getEmail());
                   userLoginResponse.setRole(userList.get(i).getRole());
                   userLoginResponse.setGender(userList.get(i).getGender());
                   responseList.add(userLoginResponse);
               }
            }
            return responseList;

        }else {
            return null;
        }

    }

    @Override
    public CommonResponse updateUser(UserUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            User user = userRepository.findByPhoneNumber(request.getPhoneNumber().trim());
            if(user != null){
                user.setFullname(request.getFullname());
                user.setEmail(request.getEmail());
                user.setGender(request.getGender());
                user.setStatus(request.getStatus());
                userRepository.save(user);
                response.setStatus(200);
                response.setMessage("Cập nhật user thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Cập nhật user thất bại !!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return response;
        }

    }

    @Override
    public CommonResponse deleteUser(String phoneNumber) {
        CommonResponse response = new CommonResponse();
        try{
            User user = userRepository.findByPhoneNumber(phoneNumber);
            if (user != null){
                user.setStatus("inactive");
                userRepository.save(user);
                response.setStatus(200);
                response.setMessage("Xóa user thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại user để xóa !!!");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Xóa user thất bại !!!");
            e.printStackTrace();
        }finally {
            return response;
        }

    }

    @Override
    public List<UserLoginResponse> searchByPhoneNumber(String keySearch) {
        List<UserLoginResponse> responseList = new ArrayList<>();
        try{
            List<User> userLoginResponse = userRepository.getAllUserByPhoneNumber(keySearch);
            if (userLoginResponse != null){
                for (int i = 0 ; i < userLoginResponse.size(); i++){
                    UserLoginResponse response = new UserLoginResponse();
                    response.setFullname(userLoginResponse.get(i).getFullname());
                    response.setPhoneNumber(userLoginResponse.get(i).getPhoneNumber());
                    response.setEmail(userLoginResponse.get(i).getEmail());
                    response.setRole(userLoginResponse.get(i).getRole());
                    response.setGender(userLoginResponse.get(i).getGender());
                    responseList.add(response);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return responseList;
        }
    }
}
