package com.MovieTicketBooking.MovieTicketBooking.Admin;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AdminTbl")
@Data
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private Integer adminId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
}
