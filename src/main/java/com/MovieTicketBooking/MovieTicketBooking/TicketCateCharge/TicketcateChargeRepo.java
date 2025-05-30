package com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketcateChargeRepo extends JpaRepository<TicketCateChargeModel,Integer> {
    TicketCateChargeModel save(TicketCateChargeModel ticketCateChargeModel);

    List<TicketCateChargeModel> findByTheatreId(Integer theatreId);
}
