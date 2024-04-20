package com.example.bookmyshow.models;

import com.example.bookmyshow.models.enums.BookingStatus;
import com.example.bookmyshow.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

@Entity
public class Booking extends BaseModel {

    //We will have to describe the cardinality for Non-Primitive data types
    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    private Date bookedAt;

    @Enumerated(EnumType.ORDINAL) //maintain the order of the enum
    private BookingStatus status;

    @ManyToMany
    private List <ShowSeat> showSeats;

    @OneToMany
    private List <Payment> payments;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private int amount;

}
