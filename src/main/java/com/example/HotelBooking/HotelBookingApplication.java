package com.example.HotelBooking;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
@EnableScheduling
public class HotelBookingApplication {

	public static void main(String[] args) {

		SpringApplication.run(HotelBookingApplication.class, args);
		System.out.println("Application Running...");
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//		// Convert the key to a byte array
//		byte[] keyBytes = key.getEncoded();
//
//		// Encode the byte array as a Base64 string
//		String base64Key = Base64.getEncoder().encodeToString(keyBytes);
//
//		// Print the Base64 encoded key
//		System.out.println("Generated Key (Base64): " + base64Key);
	}

}
