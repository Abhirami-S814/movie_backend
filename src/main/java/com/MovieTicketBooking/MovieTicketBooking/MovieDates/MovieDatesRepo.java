package com.MovieTicketBooking.MovieTicketBooking.MovieDates;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieDatesRepo extends JpaRepository<MovieDatesModel,Integer> {


    Optional<MovieDatesModel> findTopByTheatreIdAndScreenIdOrderByMovEndDesc(Integer theatreId, Integer screenId);

    List<MovieDatesModel> findByTheatreIdAndScreenId(Integer theatreId, Integer screenId);

    List<MovieDatesModel> findByTheatreId(Integer theatreId);

    List<MovieDatesModel> findByMovStartLessThanEqualAndMovEndGreaterThanEqual(LocalDate date, LocalDate date1);

    boolean existsByMovieIdAndScreenIdAndTheatreId(Integer movieId, Integer screenId, Integer theatreId);

    Optional<MovieDatesModel> findByScreenId(Integer screenId);
}
