package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepositories userRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories){
        this.userRepositories = userRepositories;
    }

    public User signUp(String email, String userName, String password){
        //1. validate if user is already in my DB.
        //if user is already exists then its an exception.
        Optional<User> userOptional = userRepositories.findByEmail(email);
        if(userOptional.isPresent()){
            System.out.println("User Already Present");
            throw new RuntimeException("User Already Exists!");
        }
        System.out.println("User does not exists in the DB. New User!");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        //2. Signup method is being used to create the user and save it and return it to response DTO
        User user = new User();
        user.setName(userName);
        user.setEmail(email);
        user.setPassWord(encodedPassword);
        user.setBookings(new ArrayList<>());

        //3. Saving the user to the Database.
        User registeredUser = userRepositories.save(user);

        return registeredUser;
    }

    public User login(String email, String password){
        //1. validate if user is present or not if not then its an exception
        Optional<User> userOptional = userRepositories.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not Present in the DB");
        }
        User existingUser = userOptional.get();

        //2. Match the given password with the encryption with the encryption stored with the user.
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password, existingUser.getPassWord())){
            throw new RuntimeException("Password is incorrect");
        }

        return existingUser;
    }

}
