package com.MovieTicketBooking.MovieTicketBooking.MovieDates;

import java.time.LocalDate;

public class MovieDatesDto {
    private Integer dateId;
    private Integer theatreId;
    private String name;
    private Integer screenId;
    private String  screenName;
    private Integer movieId;
    private String movieName;
    private LocalDate movStart;
    private LocalDate movEnd;

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
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
}
