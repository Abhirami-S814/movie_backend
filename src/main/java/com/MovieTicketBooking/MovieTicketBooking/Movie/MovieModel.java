package com.MovieTicketBooking.MovieTicketBooking.Movie;

import jakarta.persistence.*;
import lombok.Data;

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
    @Column(name = "movieposter")
    private byte[] movieposter;


    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @Column(name = "releaseDate")
    private String releaseDate;


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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
