package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.controllers.dto.LoginRequestDTO;
import com.example.bookmyshow.controllers.dto.LoginResponseDTO;
import com.example.bookmyshow.controllers.dto.SignUpRequestDTO;
import com.example.bookmyshow.controllers.dto.SignUpResponseDTO;
import com.example.bookmyshow.models.BaseModel;
import com.example.bookmyshow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
    private UserController userController;

    @Autowired
    public BookMyShowApplication(UserController userController){
        this.userController = userController;
    }

    @Override
    public void run (String... args) throws Exception {
//        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
//        requestDTO.setEmail("Nishant@Scaler.com");
//        requestDTO.setPassword("789465132");
//        requestDTO.setUserName("Nishant");
//
//        SignUpResponseDTO responseDTO = userController.signUp(requestDTO);
//        System.out.println("Status is : " + responseDTO.getStatus());
//        System.out.println("Assigned User ID is : " + responseDTO.getUserId());

        LoginRequestDTO requestDTO = new LoginRequestDTO();
        requestDTO.setEmail("Nishant@Scaler.com");
        requestDTO.setPassword("789465132");

        //789465132

        LoginResponseDTO responseDTO = userController.login(requestDTO);
        System.out.println("Status is : " + responseDTO.getStatus());
        System.out.println("Logged User ID is : " + responseDTO.getUserId());
        System.out.println("Error Message is : "+ responseDTO.getErrorMessage());
    }
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }



}
