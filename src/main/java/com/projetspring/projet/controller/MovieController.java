package com.projetspring.projet.controller;

import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok("deleted successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok(movie);
    }
}
