package com.MovieTicketBooking.MovieTicketBooking.MovieLang.TheatreEmail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreEmailRepo extends JpaRepository<TheatreEmailModel,Long> {
}
