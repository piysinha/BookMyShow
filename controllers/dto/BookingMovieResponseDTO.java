package com.example.bookmyshow.controllers.dto;

import com.example.bookmyshow.controllers.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookingMovieResponseDTO {
    private Long bookingId;
    private ResponseStatus status;
    private int amount;
}
