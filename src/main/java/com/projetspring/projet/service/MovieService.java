package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.repositories.ActorRepository;
import com.projetspring.projet.repositories.MovieRepository;
import com.projetspring.projet.responses.MovieWithActorsDTO;
import com.projetspring.projet.responses.utils.MovieMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            actorRepository.save(actor);
        }
        movieRepository.save(movie);
    }

    public List<MovieWithActorsDTO> findAll() {
        List<Movie> movies = movieRepository.getAllByJPQL();
        System.out.println(movies);
        List<MovieWithActorsDTO> movieWithActorsDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            movieWithActorsDTOS.add(MovieMapper.movieToMovieWithActorsDTP(movie));
        }
        return movieWithActorsDTOS;
    }
}
