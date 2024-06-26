package com.example.bookmyshow.models;

import com.example.bookmyshow.models.enums.PaymentStatus;
import cucumber.api.java.zh_cn.那么;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String phoneNumber;
    private String passWord;

    @OneToMany
    private List <Booking> bookings;

}
