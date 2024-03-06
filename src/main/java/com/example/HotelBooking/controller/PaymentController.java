package com.example.HotelBooking.controller;

import com.example.HotelBooking.model.Elements;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    @Value("${secretKey}")
    String SecretKey;
    @Value("${product_code}")
    String product_code;
    @PostMapping("/payment")
    public Elements key(@RequestBody Elements element) {
        String transaction_uuid = generateUUID();
        String total = element.getTotal_amount();
        String tid = transaction_uuid;
        String pid = product_code;

        String message = String.format("total_amount=%s,transaction_uuid=%s,product_code=%s", total, tid, pid);
        System.out.println(message);
        String signature = generateHmacSHA256Signature(SecretKey, message);
        element.setSignature(signature);
        element.setProduct_code(product_code);
        element.setTransaction_uuid(transaction_uuid);
        element.setTotal_amount(total);
        return element;
    }

    private String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String generateHmacSHA256Signature(String secret, String message) {
        try {
            Mac sha256_MAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_MAC.init(secret_key);
            byte[] hash = sha256_MAC.doFinal(message.getBytes());
            return Base64.encodeBase64String(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null; // Return null or throw an exception based on your error handling strategy
        }
    }
}
