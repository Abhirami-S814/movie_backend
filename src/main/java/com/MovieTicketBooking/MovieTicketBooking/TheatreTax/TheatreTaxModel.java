package com.MovieTicketBooking.MovieTicketBooking.TheatreTax;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TheatreTaxTbl")
@Data
public class TheatreTaxModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taxId;

    private Integer theatreId;

    private Double taxPercentage;

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
}
