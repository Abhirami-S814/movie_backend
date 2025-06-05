package com.MovieTicketBooking.MovieTicketBooking.TheatreScreen;

import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TheatreScreenMovDTO {

    private Integer theatreId;
    private String name; // Theatre Name
    private Integer screenId;
    private String screenName;
    private Integer movieId;
    private String movieName;
    private Long seatCapacity;
    private Integer availableSeats;
    private Integer dateId;
    private LocalDate movStart;
    private LocalDate movEnd;
    private List<ShowDTO> showTimes = new ArrayList<>();

    // Getters and Setters
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

    public Long getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Long seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
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


    public List<ShowDTO> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<ShowDTO> showTimes) {
        this.showTimes = showTimes;
    }
}
