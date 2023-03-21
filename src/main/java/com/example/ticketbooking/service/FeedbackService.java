package com.example.ticketbooking.service;

import com.example.ticketbooking.entity.FeedBack;
import com.example.ticketbooking.model.request.FeedBackCreateRequest;
import com.example.ticketbooking.model.request.FeedBackUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;

import java.util.List;

public interface FeedbackService {
    List<FeedBack> getAllFeedBack();

    CommonResponse createFeedBack(FeedBackCreateRequest request);

    CommonResponse updateFeedBack(FeedBackUpdateRequest request);

    CommonResponse deleteFeedBack(String feedBackId);

    List<FeedBack> getFeedBackByUserId(String userId);
}
