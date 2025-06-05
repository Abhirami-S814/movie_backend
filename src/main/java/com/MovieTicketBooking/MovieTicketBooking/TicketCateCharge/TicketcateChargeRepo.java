package com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketcateChargeRepo extends JpaRepository<TicketCateChargeModel,Integer> {
    TicketCateChargeModel save(TicketCateChargeModel ticketCateChargeModel);

    List<TicketCateChargeModel> findByTheatreId(Integer theatreId);


    Optional<TicketCateChargeModel> findByTheatreIdAndTicketcate(Integer theatreId, String categoryName);

}
