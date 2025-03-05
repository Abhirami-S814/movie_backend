package com.MovieTicketBooking.MovieTicketBooking.TheatreScreen;

import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import jakarta.persistence.*;
import lombok.Data;

import javax.lang.model.element.Name;

@Entity
@Table(name = "theatreScreen")
@Data
public class TheatreScreenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenId")
    private Integer screenId;

    @Column(name = "screenName")
    private String screenName;

    @Column(name = "seatCapacity")
    private Long seatCapacity;

    @Column(name = "theatreId")
    private Integer theatreId;


    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Long getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Long seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
}
