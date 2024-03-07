package com.example.HotelBooking.controller;

import com.example.HotelBooking.exception.UserAlreadyExistsException;
import com.example.HotelBooking.model.User;
import com.example.HotelBooking.service.IUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestingController {
    public final IUserService userService;
    @GetMapping("/hello")
    public String home(){
        return "hello world";
    }
    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registered Successfully");
        }catch(UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
