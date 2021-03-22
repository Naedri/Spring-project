package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.repository.ActorRepository;
import com.projetspring.projet.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addMovie(Movie movie) {
        for (Actor actor : movie.getActors()) {
            actorRepository.addActor(actor);
        }
        movieRepository.addMovie(movie);
    }
}
