package com.example.TicketBookingSystemwithSpringBoot.dao;

import com.example.TicketBookingSystemwithSpringBoot.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepocitory  extends JpaRepository<Movies, Integer> {

    public List<Movies> findAllByOrderByPrice();

}