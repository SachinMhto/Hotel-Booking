package com.example.HotelBooking.email;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailForFeedBack {
    private static final String BASE_URL = "http://localhost:5173";
    public void sendEmail(String recipientEmail, String subject) {
        // SMTP server configuration
        String host = "smtp.gmail.com";
        String username = "sachin.mhto.321@gmail.com";
        String password = "qwki bzca vbzs fbcm";
        int port = 587;

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create message
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            emailMessage.setSubject(subject);
            emailMessage.setText("Dear Customer,\n\nHope you enjoyed your stay at our hotel. Please feel free to provide your feedback by visiting: " + BASE_URL + "/feedback"+"\n\nRegards\nThe Management");

            // Send message
            Transport.send(emailMessage);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
