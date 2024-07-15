package com.example.TicketBookingSystemwithSpringBoot.service;

import com.example.TicketBookingSystemwithSpringBoot.entity.Movies;

import java.util.List;

public interface MoviesServiceInterface {
    List<Movies> findAll();
    Movies findById(int id);
    Movies save(Movies teacher);
    void deleteById(int id);



}
