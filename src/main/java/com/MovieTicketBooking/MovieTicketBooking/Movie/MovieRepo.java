package com.MovieTicketBooking.MovieTicketBooking.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<MovieModel,Integer> {
    List<MovieModel> findByMovieNameContainingIgnoreCase(String movieName);

    Optional<MovieModel> findByMovieId(Integer movieId);
}
