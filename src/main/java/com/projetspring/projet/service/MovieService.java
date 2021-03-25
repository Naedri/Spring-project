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
    public void addMovie(MovieWithActorsDTO movieWithActorsDTO) throws MovieCreationWithoutActorsException{
        Movie movie = MovieMapper.movieWithActorsDTOtoMovie(movieWithActorsDTO);
        if(movie.getActors().size() <= 0){
            throw new MovieCreationWithoutActorsException("Impossible de créer un film sans acteur, relation many to many requise !");
        }
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
            System.out.println("ici"+movie);
            movieWithActorsDTOS.add(MovieMapper.movieToMovieWithActorsDTO(movie));
        }
        return movieWithActorsDTOS;
    }
}
