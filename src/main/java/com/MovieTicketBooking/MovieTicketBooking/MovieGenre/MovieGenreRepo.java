package com.MovieTicketBooking.MovieTicketBooking.MovieGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepo extends JpaRepository<MovieGenreModel,Integer> {
}
