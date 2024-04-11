package com.example.TicketBookingSystemwithSpringBoot.entity;

import jakarta.persistence.*;

@Entity
@Table(name="movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="film_name")
    private String filmName;
    @Column(name="price")
    private Integer price;
    @Column(name="date")
    private String date;
    @Column(name="place")
    private Integer place;

    public Movies(){

    }

    public Movies(String filmName, Integer price, String date, Integer place) {
        this.filmName = filmName;
        this.price = price;
        this.date = date;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", filmName='" + filmName + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", place=" + place +
                '}';
    }
}
