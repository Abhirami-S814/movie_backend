package com.MovieTicketBooking.MovieTicketBooking.Booking;

public class BookingRequest {
    private Long userId;
    private Long showtimeId;
    private int ticketCateId;
    private int numberOfSeats;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowTimeId() {
        return showtimeId;
    }

    public void setShowTimeId(Long showTimeId) {
        this.showtimeId = showTimeId;
    }

    public int getTicketCateId() {
        return ticketCateId;
    }

    public void setTicketCateId(int ticketCateId) {
        this.ticketCateId = ticketCateId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

}
