package com.MovieTicketBooking.MovieTicketBooking.MovieLang;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "languageTbl")
@Data
public class MovieLangModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "langId")
    private Integer langId;

    @Column(name = "langName")
    private String langName;

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }
}
