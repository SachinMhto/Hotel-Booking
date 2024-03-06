package com.example.HotelBooking.email;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Service
public class EmailSender {

    public void sendEmail(String confirmationCode, String guestFullName, String guestEmail) {
        // Sender's email credentials
        String senderEmail = "sachin.mhto.321@gmail.com";
        String senderPassword = "oubx nqvz siwt hqwk";

        // Recipient's email address
        String recipientEmail = guestEmail;

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Booking Confirmation");

            // Construct the email body
            String emailBody = "Dear " + guestFullName + ",\n\n";
            emailBody += "Thank you for your booking. Your confirmation code is: " + confirmationCode + ".\n\n";
            emailBody += "Regards,\nYour Hotel Team";

            // Set the email body
            message.setText(emailBody);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}

