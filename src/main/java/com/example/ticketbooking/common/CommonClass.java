package com.example.ticketbooking.common;

import com.example.ticketbooking.model.response.CommonResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonClass {
    public static String NOT_EMPTY = "Không được rỗng";

    public final static CommonResponse notEmpty(){
        CommonResponse response = new CommonResponse();
        response.setStatus(417);
        response.setMessage("Số điện thoại hoặc mật khẩu không được rỗng !!!");
        return response;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
