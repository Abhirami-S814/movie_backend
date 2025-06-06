package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TicketBookingRepo extends JpaRepository<TicketBookingModel,Integer> {
    int countBookedSeatsByScreenId(Integer screenId);

    List<TicketBookingModel> findByScreenIdAndShowDateAndShowTime(Integer screenId, LocalDate showDate, LocalTime showTime);
}
