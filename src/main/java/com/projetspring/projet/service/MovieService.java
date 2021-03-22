package com.projetspring.projet.service;

import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public boolean addMovie(Movie movie) {
        try {
            movieRepository.save(movie);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
