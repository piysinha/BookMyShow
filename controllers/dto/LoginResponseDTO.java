package com.example.bookmyshow.controllers.dto;

import com.example.bookmyshow.controllers.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Long userId;
    private ResponseStatus status;
    private String errorMessage;
}
