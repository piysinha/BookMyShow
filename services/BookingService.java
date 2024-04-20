package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.models.enums.BookingStatus;
import com.example.bookmyshow.models.enums.ShowSeatStatus;
import com.example.bookmyshow.repositories.BookingRepositories;
import com.example.bookmyshow.repositories.ShowRepositories;
import com.example.bookmyshow.repositories.ShowSeatRepositories;
import com.example.bookmyshow.repositories.UserRepositories;
import gherkin.lexer.Da;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {
    private UserRepositories userRepositories;
    private ShowRepositories showRepositories;
    private ShowSeatRepositories showSeatRepositories;
    private BookingRepositories bookingRepositories;

    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepositories userRepositories, ShowRepositories showRepositories, ShowSeatRepositories showSeatRepositories, BookingRepositories bookingRepositories, PriceCalculatorService priceCalculatorService){
        this.userRepositories = userRepositories;
        this.showRepositories = showRepositories;
        this.showSeatRepositories = showSeatRepositories;
        this.bookingRepositories = bookingRepositories;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List <Long> showSeatId)
            throws UserNotFoundException, RuntimeException, ShowSeatNotAvailableException {

        // ----- Start Transaction -----

        //1. User from the user ID
        //2. get the show from show ID.
        //3. get the showseat object from the showSeatID
        //4. Check if the show seats are available or blocked after 15 min
        //5. if any showseat is not available throw an error
        //6. update the status to block. Update the lockedAt time
        //7. Save the showSeats to DB.

        //8. Create the booking object.
        //9. return the booking object to the controller.

        //----- End Transaction (Release lock) -----

        //1. Get the user
        Optional<User> optionalUser = userRepositories.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found in DB");
        }
        User bookedBy = optionalUser.get();

        //2. Get the show
        Optional<Show> optionalShow = showRepositories.findById(showId);
        if(optionalShow.isEmpty()){
            throw new RuntimeException("Show not found");
        }
        Show bookedShow = optionalShow.get();

        //3. Get show seat objects
        List<ShowSeat> showSeats =  showSeatRepositories.findAllById(showSeatId);

        //4. & 5. Check if the seats are available = Available or Blocked
        for(ShowSeat showSeat : showSeats){
            Date lockedAt = showSeat.getLockedAt();
            ShowSeatStatus status = showSeat.getStatus();
            if(status != ShowSeatStatus.AVAILABLE || (status == ShowSeatStatus.BOOKED && Duration.between(new Date().toInstant(),lockedAt.toInstant()).toMinutes() < 15)){
                    throw new ShowSeatNotAvailableException("Seat Not Available");
                }
            }
        //6. update the status to block. Update the lockedAt time
        for(ShowSeat showSeat : showSeats){
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            showSeat.setLockedAt(new Date());
        }
        //7. Save the showSeats to DB.
        List <ShowSeat> savedShowSeat =  showSeatRepositories.saveAll(showSeats);

        //8. Setting up the Booking objects
        Booking booking = new Booking();
        booking.setUser(bookedBy);
        booking.setShow(bookedShow);
        booking.setBookedAt(new Date());
        booking.setShowSeats(savedShowSeat);
        booking.setStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeat,bookedShow));
        booking.setPayments(new ArrayList<>());

        //9. Saving the booking into the DB.
        Booking savedBooking = bookingRepositories.save(booking);

        //10. Returning the object to the client
        return savedBooking;
    }
}


    //Class notes
    //Taking a look at the method level will increase the timing of the method by few mili second
    //But it will make our code much simpler
    //taking a lock on the between the steps will learn in HLD modules.
