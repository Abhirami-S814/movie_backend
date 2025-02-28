package com.MovieTicketBooking.MovieTicketBooking.MovieDates;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "movieDatesTbl")
@Data
public class MovieDatesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dateId")
    private Integer dateId;

    @Column(name = "screenId")
    private Integer screenId;

    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "movStart")
    private LocalDate movStart;

    @Column(name = "movEnd")
    private LocalDate movEnd;

    @Column(name = "theatreId")
    private Integer theatreId;

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public LocalDate getMovStart() {
        return movStart;
    }

    public void setMovStart(LocalDate movStart) {
        this.movStart = movStart;
    }

    public LocalDate getMovEnd() {
        return movEnd;
    }

    public void setMovEnd(LocalDate movEnd) {
        this.movEnd = movEnd;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }
}
