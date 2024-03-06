package com.example.HotelBooking.exception;

public class PhotoNotFoundException extends RuntimeException {
    public PhotoNotFoundException(String error_updating_photo) {
        super(error_updating_photo);
    }
}
