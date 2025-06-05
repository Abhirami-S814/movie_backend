package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBookingRepo extends JpaRepository<TicketBookingModel,Integer> {
    int countBookedSeatsByScreenId(Integer screenId);
}
