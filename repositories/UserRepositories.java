package com.example.bookmyshow.repositories;

import com.example.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository <User,Long> {
    public Optional<User> findByEmail(String emailId);
}
