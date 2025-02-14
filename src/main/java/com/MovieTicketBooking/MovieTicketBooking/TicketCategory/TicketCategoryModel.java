package com.MovieTicketBooking.MovieTicketBooking.TicketCategory;


import com.MovieTicketBooking.MovieTicketBooking.Theatre.TheatreModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticketCategoryTbl")
@Data
@JsonIgnoreProperties("theatreModel")
public class TicketCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketCateId")
    private Integer ticketCateId;

    @Column(name = "ticketCate")
    private String ticketCate;

    @Column(name = "ticketRate")
    private Double ticketRate;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private TheatreModel theatreModel;

    public Integer getTheatreId() {
        return theatreModel != null ? theatreModel.getTheatreId() : null;
    }


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

    public Double getTicketRate() {
        return ticketRate;
    }

    public void setTicketRate(Double ticketRate) {
        this.ticketRate = ticketRate;
    }

    public TheatreModel getTheatreModel() {
        return theatreModel;
    }

    public void setTheatreModel(TheatreModel theatreModel) {
        this.theatreModel = theatreModel;
    }
}
