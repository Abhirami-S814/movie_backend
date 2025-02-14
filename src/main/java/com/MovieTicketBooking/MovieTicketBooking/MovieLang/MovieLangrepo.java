package com.MovieTicketBooking.MovieTicketBooking.MovieLang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieLangrepo extends JpaRepository<MovieLangModel,Integer> {
}
