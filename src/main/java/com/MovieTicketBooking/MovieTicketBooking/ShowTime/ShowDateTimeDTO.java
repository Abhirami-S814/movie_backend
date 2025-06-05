package com.MovieTicketBooking.MovieTicketBooking.ShowTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ShowDateTimeDTO {
    private Integer theatreId;
    private Integer screenId;
    private Integer movieId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<LocalTime> showTimes;

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<LocalTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<LocalTime> showTimes) {
        this.showTimes = showTimes;
    }
}
