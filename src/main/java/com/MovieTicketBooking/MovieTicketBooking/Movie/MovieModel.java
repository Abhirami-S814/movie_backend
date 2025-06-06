package com.MovieTicketBooking.MovieTicketBooking.Movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "movieTbl")
@Data
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "movieName")
    private String movieName;

    @Column(name = "language")
    private Integer language;

    @Column(name = "genre")
    private Integer genre;

    @Lob
    @Basic(fetch = FetchType.EAGER)
//    @JsonIgnore
    @Column(name = "movieposter")
    private byte[] movieposter;


    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "releaseDate")
    private LocalDate releaseDate;


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public byte[] getMovieposter() {
        return movieposter;
    }

    public void setMovieposter(byte[] movieposter) {
        this.movieposter = movieposter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}
