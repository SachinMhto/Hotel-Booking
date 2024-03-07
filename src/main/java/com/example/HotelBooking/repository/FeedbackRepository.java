package com.example.HotelBooking.repository;

import com.example.HotelBooking.model.FeedBack;
import com.example.HotelBooking.response.FeedBackResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedBack,Long> {

    List<FeedBack> findByRoomId(Long roomId);
}
