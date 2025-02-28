package com.MovieTicketBooking.MovieTicketBooking.TicketCategory;


import com.MovieTicketBooking.MovieTicketBooking.Theatre.TheatreModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticketCategoryTbl")
@Data
public class TicketCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketCateId")
    private Integer ticketCateId;

    @Column(name = "ticketCate")
    private String ticketCate;



    public Integer getTicketCateId() {
        return ticketCateId;
    }

    public void setTicketCateId(Integer ticketCateId) {
        this.ticketCateId = ticketCateId;
    }

    public String getTicketCate() {
        return ticketCate;
    }

    public void setTicketCate(String ticketCate) {
        this.ticketCate = ticketCate;
    }



}
