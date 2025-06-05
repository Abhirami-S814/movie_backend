package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TicketCategoryBookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private Integer quantity;
    private Long pricePerTicket;
    private Long totalPrice;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    private TicketBookingModel booking;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPricePerTicket() {
        return pricePerTicket;
    }

    public void setPricePerTicket(Long pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TicketBookingModel getBooking() {
        return booking;
    }

    public void setBooking(TicketBookingModel booking) {
        this.booking = booking;
    }
}
