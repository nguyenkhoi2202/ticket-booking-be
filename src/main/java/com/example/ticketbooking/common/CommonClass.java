package com.example.ticketbooking.common;

import com.example.ticketbooking.model.response.CommonResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Integer> getDayMonthYearFromRequest (String timeInput){
        //output thứ tự là year, month, day
        List<Integer> output = new ArrayList<>();
        if(timeInput.length() >= 10){
            String year = timeInput.substring(0,4);
            String month = timeInput.substring(5,7);
            String date = timeInput.substring(8,10);
            Integer yearAfterConvert = Integer.parseInt(year);
            Integer monthAfterConvert = Integer.parseInt(month);
            Integer dateAfterConvert = Integer.parseInt(date);
            output.add(yearAfterConvert);
            output.add(monthAfterConvert);
            output.add(dateAfterConvert);
        }
        return output;
    }
}
