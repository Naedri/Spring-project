package com.projetspring.projet.controllers;


import com.projetspring.projet.exceptions.MovieCreationWithoutActorsException;
import com.projetspring.projet.exceptions.NoneExistantActorException;
import com.projetspring.projet.responses.MovieWithActorsDTO;
import com.projetspring.projet.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<MovieWithActorsDTO> findById(@PathVariable("movieId") Long movieId) {
        MovieWithActorsDTO movie = movieService.findById(movieId);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/add")
    public ResponseEntity<MovieWithActorsDTO> addMovie(@RequestBody MovieWithActorsDTO movie) {
        try {
            movie = movieService.addMovie(movie);
        } catch (MovieCreationWithoutActorsException | NoneExistantActorException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(movie);
    }

    @GetMapping("")
    public ResponseEntity<List<MovieWithActorsDTO>> findAll() {
        List<MovieWithActorsDTO> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<MovieWithActorsDTO>> getAllMoviesGreaterThan(@RequestParam Float rate) {
        List<MovieWithActorsDTO> movies = movieService.getAllMoviesGreaterThan(rate);
        return ResponseEntity.ok(movies);
    }
}
