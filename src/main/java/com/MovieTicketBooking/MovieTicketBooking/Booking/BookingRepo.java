package com.MovieTicketBooking.MovieTicketBooking.Booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<BookingModel,Long> {

    Integer countSeatsByShowTimeIdAndCategory(Long showTimeId, Integer ticketCateId);
}
