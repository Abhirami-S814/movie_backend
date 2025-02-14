package com.MovieTicketBooking.MovieTicketBooking.MovieGenre;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genreTbl")
@Data
public class MovieGenreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genreId")
    private Integer genreId;

    @Column(name = "genreName")
    private String genreName;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
