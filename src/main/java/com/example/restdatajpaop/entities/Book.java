package com.example.restdatajpaop.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
//@Table(name ="Book")
public class Book {
    //atriutos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String description;
    private Boolean online;
    private Double price;
    private LocalDate releaseDate;
    private String title;

    //    contructor
    public Book(Long id, String author, String description, Boolean online, Double price, LocalDate releaseDate, String title) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.online = online;
        this.price = price;
        this.releaseDate = releaseDate;
        this.title = title;
    }

    public Book(){
    }

    //    getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
