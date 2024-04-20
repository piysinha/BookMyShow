package com.example.bookmyshow.controllers;

import com.example.bookmyshow.controllers.dto.LoginRequestDTO;
import com.example.bookmyshow.controllers.dto.LoginResponseDTO;
import com.example.bookmyshow.controllers.dto.SignUpRequestDTO;
import com.example.bookmyshow.controllers.dto.SignUpResponseDTO;
import com.example.bookmyshow.controllers.enums.ResponseStatus;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public SignUpResponseDTO signUp(SignUpRequestDTO requestDTO){
        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        try{
            User user = userService.signUp
                    (requestDTO.getEmail(),
                    requestDTO.getUserName(),
                    requestDTO.getPassword());

            responseDTO.setUserId(user.getId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO){
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        try{
            User user = userService.login
                            (requestDTO.getEmail(),
                            requestDTO.getPassword());

            responseDTO.setUserId(user.getId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setErrorMessage(e.getMessage());
        }
        return responseDTO;
    }
}
