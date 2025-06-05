package com.MovieTicketBooking.MovieTicketBooking.TheatreScreen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheatreScreenRepo extends JpaRepository<TheatreScreenModel,Integer> {
    List<TheatreScreenModel> findByTheatreId(Integer theatreId);

    Optional<TheatreScreenModel> findByTheatreIdAndScreenId(Integer theatreId, Integer screenId);


    List<TheatreScreenModel> findByMovie_MovieId(Integer movieId);

    List<TheatreScreenModel> findByScreenId(Integer screenId);
}
