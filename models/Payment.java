package com.example.bookmyshow.models;

import com.example.bookmyshow.models.enums.PaymentProvider;
import com.example.bookmyshow.models.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Payment extends BaseModel{
    
    private String referenceNumber;

    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

}
