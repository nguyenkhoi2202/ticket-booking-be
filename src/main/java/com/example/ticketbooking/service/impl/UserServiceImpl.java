package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.common.CommonClass;
import com.example.ticketbooking.entity.User;
import com.example.ticketbooking.mail.EmailSenderService;
import com.example.ticketbooking.model.request.UserChangePasswordRequest;
import com.example.ticketbooking.model.request.UserLoginRequest;
import com.example.ticketbooking.model.request.UserRegisterRequest;
import com.example.ticketbooking.model.request.UserUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;
import com.example.ticketbooking.repository.UserRepository;
import com.example.ticketbooking.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

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
                    loginResponse.setUserId(user.getUserId());
                    loginResponse.setPhoneNumber(user.getPhoneNumber());
                    loginResponse.setFullname(user.getFullname());
                    loginResponse.setEmail(user.getEmail());
                    loginResponse.setGender(user.getGender());
                    loginResponse.setRole(user.getRole());
                    response.setData(loginResponse);
                } else if (passwordEncrypt.equals(user.getPassword().trim()) && user.getStatus().equals("locked")) {
                    response.setStatus(417);
                    response.setMessage("Tài khoản bạn đang bị tạm khóa !!!");
                }else if (passwordEncrypt.equals(user.getPassword().trim()) && user.getStatus().equals("inactive")) {
                    response.setStatus(417);
                    response.setMessage("Tài khoản bạn chưa được kích hoạt !!!");
                }
                else if (!passwordEncrypt.equals(user.getPassword().trim())) {
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
        try {
            User checkExist = userRepository.findByPhoneNumber(userRegisterRequest.getPhoneNumber().trim());
            if (checkExist == null) {
                User user = new User();
                Random random = new Random();
                String userId = "user" + userRegisterRequest.getPhoneNumber().substring(5)
                        .concat(Integer.toString(random.nextInt(99)));
                user.setUserId(userId);
                user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
                user.setPassword(CommonClass.getMD5(userRegisterRequest.getPassword().trim()));
                user.setEmail(userRegisterRequest.getEmail());
                user.setFullname(userRegisterRequest.getFullname());
                user.setGender(userRegisterRequest.getGender());
                user.setRole("user");
                user.setStatus("inactive");
                String code = RandomStringUtils.random(6, false, true);
                user.setVerifyCode(code);
                userRepository.save(user);
                response.setStatus(200);
                response.setMessage("Đăng kí tài khỏan mới thành công !!!");

                String body = "<p>Dear " + userRegisterRequest.getFullname() + ",</p>";
                body += "<p> Vui lòng click vào link bên dưới để xác thực tài khoản: </p>";
                String link = "http://localhost:3000/register/verify";
                body += "<h3> <a href=\"" + link + "\">Link</a> </h3>";
                body += "<h3>OTP của bạn là : </h3>" + code;
                body += "<p>Cảm ơn !!!  </p>";
                emailSenderService.sendSimpleEmail(userRegisterRequest.getEmail().trim(), "Xác thực tài khoản đăng kí",
                        body);
            } else {
                response.setStatus(417);
                response.setMessage("Tài khoản đã tồn tại !!!");
            }

        } catch (Exception e) {
            response.setStatus(417);
            response.setMessage("Đăng kí tài khỏan mới thất bại !!!");
            e.printStackTrace();
        } finally {
            return response;
        }

    }
    @Override
    public List<UserLoginResponse> getAllUser() {
        List<User> userList = userRepository.getAllUser();
        if (userList != null){
            List<UserLoginResponse> responseList = new ArrayList<>();
            for (int i = 0; i < userList.size(); i++){
                   UserLoginResponse userLoginResponse = new UserLoginResponse();
                   userLoginResponse.setUserId(userList.get(i).getUserId());
                   userLoginResponse.setPhoneNumber(userList.get(i).getPhoneNumber());
                   userLoginResponse.setFullname(userList.get(i).getFullname());
                   userLoginResponse.setEmail(userList.get(i).getEmail());
                   userLoginResponse.setRole(userList.get(i).getRole());
                   userLoginResponse.setGender(userList.get(i).getGender());
                   userLoginResponse.setStatus(userList.get(i).getStatus());
                   responseList.add(userLoginResponse);
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
                user.setStatus("locked");
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
                    response.setUserId(userLoginResponse.get(i).getUserId());
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

    @Override
    public void verify(String code) {
        try{
            User user = userRepository.getUserByVerificationCode(code);
            if (user != null){
                user.setStatus("active");
                userRepository.save(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public CommonResponse createAdmin(UserRegisterRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            User checkExist = userRepository.findByPhoneNumber(request.getPhoneNumber().trim());
            if (checkExist == null) {
                User user = new User();
                Random random = new Random();
                String userId = "admin" + request.getPhoneNumber().substring(5)
                        .concat(Integer.toString(random.nextInt(99)));
                user.setUserId(userId);
                user.setPhoneNumber(request.getPhoneNumber());
                user.setPassword(CommonClass.getMD5(request.getPassword().trim()));
                user.setEmail(request.getEmail());
                user.setFullname(request.getFullname());
                user.setGender(request.getGender());
                user.setRole("admin");
                user.setStatus("active");
                userRepository.save(user);
                response.setStatus(200);
                response.setMessage("Đăng kí tài khỏan admin thành công !!!");

            } else {
                response.setStatus(417);
                response.setMessage("Tài khoản đã tồn tại !!!");
            }

        } catch (Exception e) {
            response.setStatus(417);
            response.setMessage("Đăng kí tài khỏan admin thất bại !!!");
            e.printStackTrace();
        } finally {
            return response;
        }
    }

    @Override
    public CommonResponse changePassword(UserChangePasswordRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            User user = userRepository.findByPhoneNumber(request.getPhoneNumber().trim());
            if(user != null){
                if (CommonClass.getMD5(request.getOldPassword().trim()).equals(user.getPassword().trim())){
                    user.setPassword(CommonClass.getMD5(request.getNewPassword().trim()));
                    userRepository.save(user);
                    response.setStatus(200);
                    response.setMessage("Đổi mật khẩu thành công");
                }else{
                    response.setStatus(417);
                    response.setMessage("Mật khẩu cũ không khớp!!!");
                }

            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại tài khoản này !!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return response;
        }
    }
}
