package com.example.bookmyshow.controllers;

import com.example.bookmyshow.controllers.dto.BookingMovieRequestDTO;
import com.example.bookmyshow.controllers.dto.BookingMovieResponseDTO;
import com.example.bookmyshow.controllers.enums.ResponseStatus;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookingMovieResponseDTO bookMovie(BookingMovieRequestDTO requestDTO) throws UserNotFoundException, ShowSeatNotAvailableException {
        BookingMovieResponseDTO responseDTO = new BookingMovieResponseDTO();
        Booking booking = bookingService.bookMovie(
                requestDTO.getUserId(),
                requestDTO.getShowId(),
                requestDTO.getShowSeatIds()
        );
        responseDTO.setBookingId(booking.getId());
        responseDTO.setAmount(booking.getAmount());
        responseDTO.setStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }
}
