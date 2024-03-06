package com.example.HotelBooking.service;

import com.example.HotelBooking.model.BookedRoom;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface IBookingService {
    void cancelBooking(Long bookingId);


    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    List<BookedRoom> getAllBookings();
}
