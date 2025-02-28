package com.MovieTicketBooking.MovieTicketBooking.TicketCharge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketChargeRepo extends JpaRepository<TicketChargeModel,Integer> {
    Optional<TicketChargeModel> findByTicketCateIdAndTheatreId(Integer ticketCateId, Integer theatreId);

    List<TicketChargeModel> findByTheatreId(Integer theatreId);
}
