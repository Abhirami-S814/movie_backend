package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "TicketbookingTbl")
@Data
public class TicketBookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private Integer userId;
    private String userName;

    private Integer movieId;
    private String movieName;

    private Integer theatreId;
    private String theatreName;

    private Integer screenId;
    private String screenName;

    private LocalDate showDate;
    private LocalTime showTime;

    private Long totalBeforeTax;
    private Double taxPercentage;
    private Double taxAmount;
    private Double totalWithTax;

    private Long seatCapacityBeforeBooking;
    private Long seatCapacityAfterBooking;


    @Column(name = "bookingDateTime")
    private LocalDateTime bookingDateTime;


    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TicketCategoryBookingModel> categoryBookings;


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getTotalBeforeTax() {
        return totalBeforeTax;
    }

    public void setTotalBeforeTax(Long totalBeforeTax) {
        this.totalBeforeTax = totalBeforeTax;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotalWithTax() {
        return totalWithTax;
    }

    public void setTotalWithTax(Double totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public List<TicketCategoryBookingModel> getCategoryBookings() {
        return categoryBookings;
    }

    public void setCategoryBookings(List<TicketCategoryBookingModel> categoryBookings) {
        this.categoryBookings = categoryBookings;
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

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
}
