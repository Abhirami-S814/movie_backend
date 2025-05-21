package com.MovieTicketBooking.MovieTicketBooking.Booking;

import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryModel;
import com.MovieTicketBooking.MovieTicketBooking.User.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
@Data
public class BookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private Long bookingId;

    @ManyToOne
    private UserModel userModel;

    @ManyToOne
    private ShowTimeModel showTimeModel;

    @ManyToOne
    private TicketCategoryModel ticketCategory;

    private int numberOfSeats;

    private double totalAmount;

    private LocalDateTime bookingTime;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public ShowTimeModel getShowTimeModel() {
        return showTimeModel;
    }

    public void setShowTimeModel(ShowTimeModel showTimeModel) {
        this.showTimeModel = showTimeModel;
    }

    public TicketCategoryModel getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategoryModel ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
