package com.example.bookmyshow.controllers.dto;

import com.example.bookmyshow.controllers.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    //If user is already registered give back the user ID as a confirmation.
    private Long userId;
    private ResponseStatus status;
}
