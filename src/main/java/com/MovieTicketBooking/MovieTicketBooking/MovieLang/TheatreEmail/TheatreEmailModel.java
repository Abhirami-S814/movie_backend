package com.MovieTicketBooking.MovieTicketBooking.MovieLang.TheatreEmail;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "theateEmail")
@Data
public class TheatreEmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatreEmailId")
    private Long theatreEmailId;

    @Column(name = "toEmail")
    private String toEmail;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Column(name = "sentTime")
    private LocalDateTime sentTime;

    @Column(name = "status")
    private String status;

    public Long getTheatreEmailId() {
        return theatreEmailId;
    }

    public void setTheatreEmailId(Long theatreEmailId) {
        this.theatreEmailId = theatreEmailId;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
