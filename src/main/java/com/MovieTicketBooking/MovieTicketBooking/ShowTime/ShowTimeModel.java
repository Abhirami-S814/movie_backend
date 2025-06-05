package com.MovieTicketBooking.MovieTicketBooking.ShowTime;

import com.MovieTicketBooking.MovieTicketBooking.Theatre.TheatreModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "showtimeTbl")
@Data
public class ShowTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtimeId")
    private Integer showtimeId;

    @Column(name = "dateId")
    private Integer dateId;

    @Column(name = "showStart")
    private LocalTime showStart;

    private Integer screenId;

    private Integer theatreId;

    private Integer movieId;


    public Integer getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Integer showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public LocalTime getShowStart() {
        return showStart;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
    }



    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }
}