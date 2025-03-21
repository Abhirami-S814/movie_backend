package com.MovieTicketBooking.MovieTicketBooking.SeatAvailability;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seatAvailable")
@Data
public class SeatAvailabilityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatId")
    private Long seatId;

    @Column(name = "screenId", nullable = false)
    private Long screenId; // This is enough to link to theater and movie

    @Column(name = "totalSeats", nullable = false)
    private Integer totalSeats;

    @Column(name = "availablSeats", nullable = false)
    private Integer availableSeats;

    public SeatAvailabilityModel(Long screenId, Integer totalSeats) {
        this.screenId = screenId;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; // Initially all seats are available
    }

    public SeatAvailabilityModel(Integer screenId, Long seatCapacity) {
    }

    public void bookSeats(int seatsToBook) {
        if (availableSeats >= seatsToBook) {
            this.availableSeats -= seatsToBook;
        } else {
            throw new IllegalStateException("Not enough available seats.");
        }
    }

    public void cancelSeats(int seatsToCancel) {
        if ((availableSeats + seatsToCancel) <= totalSeats) {
            this.availableSeats += seatsToCancel;
        } else {
            throw new IllegalStateException("Cannot exceed total seat capacity.");
        }
    }
}
