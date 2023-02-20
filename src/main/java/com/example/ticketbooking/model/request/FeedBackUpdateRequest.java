package com.example.ticketbooking.model.request;

import com.example.ticketbooking.entity.FeedBack;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link FeedBack} entity
 */
@Data
public class FeedBackUpdateRequest implements Serializable {
    private final String feedbackId;
    private final String message;
    private final String response;
    private final String status;
}