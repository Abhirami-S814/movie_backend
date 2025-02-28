package com.MovieTicketBooking.MovieTicketBooking.TicketCharge;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticketChargeTbl")
@Data
public class TicketChargeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chargeId")
    private Integer chargeId;

    @Column(name = "ticketCateId")
    private Integer ticketCateId;

    @Column(name = "theatreId")
    private Integer theatreId;

    @Column(name = "charge")
    private Long charge;

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getTicketCateId() {
        return ticketCateId;
    }

    public void setTicketCateId(Integer ticketCateId) {
        this.ticketCateId = ticketCateId;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }
}
