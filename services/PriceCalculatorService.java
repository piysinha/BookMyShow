package com.example.bookmyshow.services;

import com.example.bookmyshow.models.SeatType;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.ShowSeatType;
import com.example.bookmyshow.repositories.ShowSeatTypeRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepositories showSeatTypeRepositories;

    public PriceCalculatorService(ShowSeatTypeRepositories showSeatTypeRepositories){
        this.showSeatTypeRepositories = showSeatTypeRepositories;
    }
    public int calculatePrice(List<ShowSeat> showSeats, Show show){
        //1.Given the show get the show seat time and their corresponding price.
        List<ShowSeatType> dbShowSeatTypes = showSeatTypeRepositories.findAllByShow(show);

        //2.
        int amount = 0;
        for(ShowSeat selectedShowSeat: showSeats){
            SeatType selectedSeatType = selectedShowSeat.getSeat().getSeatType();
            for(ShowSeatType dbShowSeatType : dbShowSeatTypes){
                if(dbShowSeatType.getSeatType() == selectedSeatType){
                    amount += dbShowSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
