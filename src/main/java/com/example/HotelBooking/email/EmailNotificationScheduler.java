package com.example.HotelBooking.email;

import com.example.HotelBooking.model.BookedRoom;
import com.example.HotelBooking.model.User;
import com.example.HotelBooking.service.BookingService;
import com.example.HotelBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmailNotificationScheduler {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EmailForFeedBack emailService;

//   @Scheduled(cron = "*/15 * * * * *")//15 sec
//    @Scheduled(cron = "0 */15 * * * *")// 15min
@Scheduled(cron = "0 0 12 * * ?")//12 o'clock every 24 hrs
public void sendOverdueBookingNotifications() {
        LocalDate today = LocalDate.now();
        List<BookedRoom> bookings = bookingService.findAllBookings();

        for (BookedRoom user : bookings) {
            if (user.getCheckOutDate().isEqual(today)) {
                sendNotificationEmail(user);
            }
        }
    }

    private void sendNotificationEmail(BookedRoom user) {
        String recipientEmail = user.getGuestEmail();
        System.out.println(recipientEmail);
        // Construct email content
        String subject = "Thank you for staying!!";

        emailService.sendEmail(recipientEmail, subject);
    }
    }
