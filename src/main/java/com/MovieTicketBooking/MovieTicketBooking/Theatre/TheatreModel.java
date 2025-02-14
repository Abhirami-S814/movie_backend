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


    @Column(name = "contact")
    private String contact;


    @OneToMany(mappedBy = "theatreModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TicketCategoryModel> ticketCategories;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<TicketCategoryModel> getTicketCategories() {
        return ticketCategories;
    }

    public void setTicketCategories(List<TicketCategoryModel> ticketCategories) {
        this.ticketCategories = ticketCategories;
    }
}
