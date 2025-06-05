package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookingRequestDTO {

    private Integer userId;
    private Integer movieId;
    private String movieName;
    private Integer theatreId;
    private String theatreName;
    private Integer screenId;
    private String screenName;
    private LocalDate showDate;
    private LocalTime showTime;

    private Long seatCapacityBeforeBooking;
    private Long seatCapacityAfterBooking;


    private List<CategoryBooking> categoryBookings;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
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

    public Long getSeatCapacityBeforeBooking() {
        return seatCapacityBeforeBooking;
    }

    public void setSeatCapacityBeforeBooking(Long seatCapacityBeforeBooking) {
        this.seatCapacityBeforeBooking = seatCapacityBeforeBooking;
    }

    public Long getSeatCapacityAfterBooking() {
        return seatCapacityAfterBooking;
    }

    public void setSeatCapacityAfterBooking(Long seatCapacityAfterBooking) {
        this.seatCapacityAfterBooking = seatCapacityAfterBooking;
    }

    public List<CategoryBooking> getCategoryBookings() {
        return categoryBookings;
    }

    public void setCategoryBookings(List<CategoryBooking> categoryBookings) {
        this.categoryBookings = categoryBookings;
    }
}
