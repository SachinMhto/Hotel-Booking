package com.example.HotelBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Elements {
    private String transaction_uuid;
    private String signature;
    private String total_amount;
    private String product_code;
}
