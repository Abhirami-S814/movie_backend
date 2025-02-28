package com.MovieTicketBooking.MovieTicketBooking.ShowTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ShowTimeDto {
    private Integer theatreId;
    private String name;
    private Integer dateId;
    private LocalDate movStart;
    private LocalDate movEnd;
    private Integer showTimeId;
    private LocalTime showStart;
    private LocalTime showEnd;

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

    public Integer getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(Integer showTimeId) {
        this.showTimeId = showTimeId;
    }

    public LocalTime getShowStart() {
        return showStart;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
    }

    public LocalTime getShowEnd() {
        return showEnd;
    }

    public void setShowEnd(LocalTime showEnd) {
        this.showEnd = showEnd;
    }
}
