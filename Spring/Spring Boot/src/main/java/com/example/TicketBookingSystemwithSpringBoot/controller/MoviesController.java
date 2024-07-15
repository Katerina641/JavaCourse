package com.example.TicketBookingSystemwithSpringBoot.controller;

import com.example.TicketBookingSystemwithSpringBoot.entity.Movies;
import com.example.TicketBookingSystemwithSpringBoot.service.MoviesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    private MoviesService moviesService;

    public MoviesController(MoviesService mService){
        moviesService = mService;
    }
   @GetMapping("/get")
    public String getMovies(Model model){
       // get movies from the database
       List<Movies> movies = moviesService.findAll();
       //add movies to the spring model
       model.addAttribute("movies", movies);
       return "get-movies";
    }
    @GetMapping("/addPlace")//("/addTeacher")
    public String addPlace(Model model){
        Movies movies = new Movies();
        model.addAttribute("movies", movies);
        return "movies-form";
    }



    @PostMapping("/savePlaces")
    public String savePlaces(@ModelAttribute("movies") Movies movies){
        moviesService.save(movies);
        return "redirect:/movies/get";
    }
    @GetMapping("/bookPlace")
    public String BookPlace(@RequestParam("moviesId") int id){
       moviesService.deleteById(id);
        return "redirect:/movies/get";
    }





}
