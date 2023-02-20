package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.FeedBack;
import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.model.request.FeedBackCreateRequest;
import com.example.ticketbooking.model.request.FeedBackUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.repository.FeedBackRepository;
import com.example.ticketbooking.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedBackRepository feedBackRepository;


    @Override
    public List<FeedBack> getAllFeedBack() {
        List<FeedBack> feedBackList = feedBackRepository.getAllFeedBack();
        if (feedBackList != null){
            List<FeedBack> responseList = new ArrayList<>();
            for (int i = 0; i < feedBackList.size(); i++){
                if (feedBackList.get(i).getStatus().equals("active")){
                    responseList.add(feedBackList.get(i));
                }
            }
            return  responseList;
        }else {
            return null;
        }
    }

    @Override
    public CommonResponse createFeedBack(FeedBackCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            FeedBack feedBack = new FeedBack();
            feedBack.setFeedbackId("fe" + request.getMessage().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            feedBack.setMessage(request.getMessage());
            feedBack.setResponse(request.getResponse());
            feedBack.setUserId(request.getUserId());
            feedBack.setStatus("active");
            feedBackRepository.save(feedBack);
            response.setStatus(200);
            response.setMessage("Tạo mới feedback thành công");
        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Tạo mới feedback thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse updateFeedBack(FeedBackUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            FeedBack feedBack = feedBackRepository.findById(request.getFeedbackId()).get();
            if(feedBack != null){
                feedBack.setMessage(request.getMessage());
                feedBack.setResponse(request.getResponse());
                feedBack.setStatus(request.getStatus());
                feedBackRepository.save(feedBack);
                response.setStatus(200);
                response.setMessage("Cập nhật thành công");

            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại feedback để cập nhật");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Cập nhật thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse deleteFeedBack(String feedBackId) {
        CommonResponse response = new CommonResponse();
        try{
            FeedBack feedBack = feedBackRepository.findById(feedBackId).get();
            if (feedBack != null){
                feedBack.setStatus("inactive");
                feedBackRepository.save(feedBack);
                response.setStatus(200);
                response.setMessage("Xóa thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại feedback để xóa");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Xóa thất bại");
            e.printStackTrace();
        }finally {
            return  response;
        }
    }
}
