package com.projetspring.projet.controllers;

import com.projetspring.projet.responses.MovieWithActorsDTO;
import com.projetspring.projet.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MovieWithActorsDTO> addMovie(@RequestBody MovieWithActorsDTO movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("")
    public ResponseEntity<List<MovieWithActorsDTO>> findAll() {
        List<MovieWithActorsDTO> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }
}
