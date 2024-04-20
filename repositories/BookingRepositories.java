package com.example.bookmyshow.repositories;

import com.example.bookmyshow.models.Booking;
import org.hibernate.query.criteria.JpaInPredicate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BookingRepositories extends JpaRepository <Booking, Long> {

}
