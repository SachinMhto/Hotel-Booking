package com.example.HotelBooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(name="check-in")
    private LocalDate checkInDate;
    @Column(name="check-out")
    private LocalDate checkOutDate;
    @Column(name="guest_FullName")
    private String guestFullName;
    @Column(name="guest_Email")
    private String guestEmail;
    @Column(name="adults")
    private int NumOfAdults;
    @Column(name="children")
    private int NumOfChildren;
    @Column(name="total_guests")
    private int totalNumOfGuest;
    @Column(name="confirmation_Code")
    private String bookingConfirmationCode;
    @JoinColumn(name="room_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Room room;

    public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest=this.NumOfChildren+this.NumOfAdults;
    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getBookingId() {
        return bookingId;
    }
}

