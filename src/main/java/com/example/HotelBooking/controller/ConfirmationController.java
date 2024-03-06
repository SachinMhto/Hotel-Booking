package com.example.HotelBooking.controller;

import com.example.HotelBooking.email.EmailSender;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/code")
public class ConfirmationController {
    @Autowired
    private EmailSender emailSender;
public final ObjectMapper objectMapper;
    @PostMapping("/confirmation")
    public ResponseEntity<String> code(@RequestBody String confirmation) {
        try {
            // Parse the JSON string into a JsonNode object
            JsonNode jsonNode = objectMapper.readTree(confirmation);

            // Extract the required fields
            String confirmationCode = jsonNode.get("confirmationCode").asText();
            String guestFullName = jsonNode.get("booking").get("guestFullName").asText();
            String guestEmail = jsonNode.get("booking").get("guestEmail").asText();

            // Log the extracted data
            System.out.println("Confirmation Code: " + confirmationCode);
            System.out.println("Guest Full Name: " + guestFullName);
            System.out.println("Guest Email: " + guestEmail);

            emailSender.sendEmail(confirmationCode, guestFullName, guestEmail);
            // Return a success response
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }
}
