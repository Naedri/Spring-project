package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.exceptions.MovieCreationWithoutActorsException;
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
    public void addMovie(MovieWithActorsDTO movieWithActorsDTO) throws MovieCreationWithoutActorsException {
        if (movieWithActorsDTO.getActors().size() <= 0) {
            throw new MovieCreationWithoutActorsException("Impossible de créer un film sans acteur, relation many to many requise !");
        }
        Movie movie = MovieMapper.movieWithActorsDTOtoMovie(movieWithActorsDTO);
        for (Actor actor : movie.getActors()) {
            actorRepository.save(actor);
        }
        movieRepository.save(movie);
    }

    public List<MovieWithActorsDTO> findAll() {
        List<Movie> movies = movieRepository.getAllByJPQL();
        List<MovieWithActorsDTO> movieWithActorsDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            movieWithActorsDTOS.add(MovieMapper.movieToMovieWithActorsDTO(movie));
        }
        return movieWithActorsDTOS;
    }

    public List<MovieWithActorsDTO> getAllMoviesGreaterThan(Long rate) {
        List<Movie> movies = movieRepository.getAllMoviesGreaterThanByJPQL(rate);
        List<MovieWithActorsDTO> movieWithActorsDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            movieWithActorsDTOS.add(MovieMapper.movieToMovieWithActorsDTO(movie));
        }
        return movieWithActorsDTOS;
    }
}
