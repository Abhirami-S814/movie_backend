package com.MovieTicketBooking.MovieTicketBooking.Theatre;


import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "theatreTbl")
@Data
public class TheatreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatreId")
    private Integer theatreId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "location")
    private String location;

    @Column(name = "noOfScreens")
    private Integer noOfScreens;

    @Column(name = "contact")
    private String contact;


    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNoOfScreens() {
        return noOfScreens;
    }

    public void setNoOfScreens(Integer noOfScreens) {
        this.noOfScreens = noOfScreens;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
