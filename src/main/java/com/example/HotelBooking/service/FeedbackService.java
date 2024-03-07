package com.example.HotelBooking.service;

import com.example.HotelBooking.model.FeedBack;
import com.example.HotelBooking.repository.FeedbackRepository;
import com.example.HotelBooking.response.FeedBackResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService implements IFeedbackService{
    private final FeedbackRepository feedbackRepository;

    @Override
    public FeedBack saveTo(FeedBack feedBack) {
        FeedBack fb=new FeedBack();
        fb.setRoomId(feedBack.getRoomId());
        fb.setGuestName(feedBack.getGuestName());
        fb.setGuestEmail(feedBack.getGuestEmail());
        fb.setFeedback(feedBack.getFeedback());
        return feedbackRepository.save(fb);
    }

    @Override
    public FeedBackResponse giveAll() {
        return (FeedBackResponse) feedbackRepository.findAll();
    }

    @Override
    public List<FeedBack> findByRoomId(Long roomId) {
        return feedbackRepository.findByRoomId(roomId);
    }

}
