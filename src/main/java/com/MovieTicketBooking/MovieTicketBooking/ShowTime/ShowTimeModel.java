package com.MovieTicketBooking.MovieTicketBooking.ShowTime;

import com.MovieTicketBooking.MovieTicketBooking.Theatre.TheatreModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "showtimeTbl")
@Data
public class ShowTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtimeId")
    private Integer showtimeId;

    @Column(name = "showStart")
    private LocalTime showStart;

    @Column(name = "showEnd")
    private LocalTime showEnd;

    private Integer theatreId;

    public Integer getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Integer showtimeId) {
        this.showtimeId = showtimeId;
    }

    public LocalTime getShowStart() {
        return showStart;
    }

    public void setShowStart(LocalTime showStart) {
        this.showStart = showStart;
    }

    public LocalTime getShowEnd() {
        return showEnd;
    }

    public void setShowEnd(LocalTime showEnd) {
        this.showEnd = showEnd;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }
}