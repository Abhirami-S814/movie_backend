package com.MovieTicketBooking.MovieTicketBooking.ShowTime;

import java.time.LocalTime;

public class ShowDTO {
    private Integer showTimeId;
    private LocalTime showStart;

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
}
