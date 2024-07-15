package com.example.TicketBookingSystemwithSpringBoot.service;

import com.example.TicketBookingSystemwithSpringBoot.dao.MoviesRepocitory;
import com.example.TicketBookingSystemwithSpringBoot.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService implements MoviesServiceInterface{

    private MoviesRepocitory moviesRepocitory;

    @Autowired
    public MoviesService(MoviesRepocitory mRepocitory){
        moviesRepocitory = mRepocitory;
    }
    @Override
    public List<Movies> findAll() {
        return moviesRepocitory.findAllByOrderByPrice();
    }

    @Override
    public Movies findById(int id) {
        Optional<Movies> result = moviesRepocitory.findById(id);
        Movies movies = null;

        if(result.isPresent()){
            movies = result.get();
        } else {
            throw new RuntimeException("Couldn't find movies id: " + id);
        }

        return movies;
    }


    @Override
    public Movies save(Movies movies) {
        return moviesRepocitory.save(movies);
    }
    @Override
    public void deleteById(int id) {
        moviesRepocitory.deleteById(id);
    }
}
