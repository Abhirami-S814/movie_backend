package com.MovieTicketBooking.MovieTicketBooking.SeatAvailability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatAvailabilityRepo extends JpaRepository<SeatAvailabilityModel,Long> {

}
