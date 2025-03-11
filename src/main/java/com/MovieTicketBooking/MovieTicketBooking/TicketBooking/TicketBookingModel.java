package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticketBookingTbl")
@Data
public class TicketBookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private Integer bookingId;

    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "theatreId")
    private Integer theatreId;

    @Column(name = "screenId")
    private Integer screenId;

    @Column(name = "showdateId")
    private Integer showdateId;

    @Column(name = "showTimeId")
    private Integer showTimeId;



}
