package com.MovieTicketBooking.MovieTicketBooking.TheatreTax;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreTaxRepo extends JpaRepository<TheatreTaxModel,Integer> {
    Optional<TheatreTaxModel> findByTheatreId(Integer theatreId);
}
