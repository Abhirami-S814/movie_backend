package com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticketcatechargetbl")
@Data
public class TicketCateChargeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketcatechargeId")
    private Integer ticketcatechargeId;

    @Column(name = "ticketcate")
    private String ticketcate;

    @Column(name = "ticketcharge")
    private Long ticketcharge;

    private Integer theatreId;

    public Integer getTicketcatechargeId() {
        return ticketcatechargeId;
    }

    public void setTicketcatechargeId(Integer ticketcatechargeId) {
        this.ticketcatechargeId = ticketcatechargeId;
    }

    public String getTicketcate() {
        return ticketcate;
    }

    public void setTicketcate(String ticketcate) {
        this.ticketcate = ticketcate;
    }

    public Long getTicketcharge() {
        return ticketcharge;
    }

    public void setTicketcharge(Long ticketcharge) {
        this.ticketcharge = ticketcharge;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }
}
