package com.MovieTicketBooking.MovieTicketBooking.TicketCharge;

public class TicketChargeDto {
    private Integer theatreId;
    private Integer ChargeId;
    private Integer ticketCateId;
    private String ticketCate;
    private String theatreName;
    private Long charge;


    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Integer getChargeId() {
        return ChargeId;
    }

    public void setChargeId(Integer chargeId) {
        ChargeId = chargeId;
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

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }
}
