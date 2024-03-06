package com.example.HotelBooking.exception;

public class RoleAlreadyExistException extends RuntimeException{
    public RoleAlreadyExistException(String s) {
        super(s);
    }
}
