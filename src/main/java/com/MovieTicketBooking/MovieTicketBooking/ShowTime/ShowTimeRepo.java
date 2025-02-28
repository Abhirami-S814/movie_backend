package com.MovieTicketBooking.MovieTicketBooking.ShowTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowTimeRepo extends JpaRepository<ShowTimeModel, Integer> {

    boolean existsByShowtimeIdAndTheatreId(Integer showTimeId, Integer theatreId);

    ResponseEntity<?> deleteByShowtimeIdAndTheatreId(Integer showTimeId, Integer theatreId);


    List<ShowTimeModel> findByTheatreId(Integer theatreId);

    Optional<ShowTimeModel> findByTheatreIdAndShowtimeIdAndDateId(Integer theatreId, Integer showtimeId, Integer dateId);

    Optional<ShowTimeModel> findByTheatreIdAndShowtimeId(Integer theatreId, Integer showtimeId);
}
