package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.exceptions.MovieCreationWithoutActorsException;
import com.projetspring.projet.exceptions.NoneExistantActorException;
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
    public MovieWithActorsDTO addMovie(MovieWithActorsDTO movieWithActorsDTO) throws MovieCreationWithoutActorsException, NoneExistantActorException {
        if (movieWithActorsDTO.getActors().isEmpty()) {
            throw new MovieCreationWithoutActorsException("Impossible de créer un film sans acteur, relation many to many requise !");
        }
        Movie movie = MovieMapper.movieWithActorsDTOtoMovie(movieWithActorsDTO);
        for (Actor actor : movie.getActors()) {
            Long actorId = actor.getId();
            if (actorId != null) {
                actor = actorRepository.findById(actorId).orElseThrow(() -> new NoneExistantActorException(String.format("Impossible d'ajouter le film, l'acteur id:%s est inexistant!", actorId)));
            }
            actorRepository.save(actor);
        }
        movieRepository.save(movie);
        return MovieMapper.movieToMovieWithActorsDTO(movie);
    }

    public List<MovieWithActorsDTO> findAll() {
        List<Movie> movies = movieRepository.getAllByJPQL();
        List<MovieWithActorsDTO> movieWithActorsDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            movieWithActorsDTOS.add(MovieMapper.movieToMovieWithActorsDTO(movie));
        }
        return movieWithActorsDTOS;
    }

    public List<MovieWithActorsDTO> getAllMoviesGreaterThan(Float rate) {
        List<Movie> movies = movieRepository.getAllMoviesGreaterThanByJPQL(rate);
        List<MovieWithActorsDTO> movieWithActorsDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            movieWithActorsDTOS.add(MovieMapper.movieToMovieWithActorsDTO(movie));
        }
        return movieWithActorsDTOS;
    }
}
