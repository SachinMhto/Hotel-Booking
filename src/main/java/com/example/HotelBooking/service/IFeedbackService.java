package com.example.HotelBooking.service;

import com.example.HotelBooking.model.FeedBack;
import com.example.HotelBooking.response.FeedBackResponse;

import java.util.List;

public interface IFeedbackService {
    FeedBack saveTo(FeedBack feedBack);

    FeedBackResponse giveAll();

    List<FeedBack> findByRoomId(Long roomId);
}
