package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import java.time.LocalDate;
import java.time.LocalTime;

public class AvailableSeatsDTO {
    private Integer screenId;
    private String screenName;
    private LocalDate showDate;
    private LocalTime showTime;
    private Long availableSeats;

    public AvailableSeatsDTO(Integer screenId, String screenName, LocalDate showDate, LocalTime showTime, Long availableSeats) {
        this.screenId = screenId;
        this.screenName = screenName;
        this.showDate = showDate;
        this.showTime = showTime;
        this.availableSeats = availableSeats;
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

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public Long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Long availableSeats) {
        this.availableSeats = availableSeats;
    }
}
