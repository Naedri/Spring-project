package com.projetspring.projet.responses.utils;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.responses.ActorMiniDTO;
import com.projetspring.projet.responses.MovieWithActorsDTO;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {
    public static final MovieWithActorsDTO movieToMovieWithActorsDTO(Movie movie) {
        MovieWithActorsDTO movieWithActorsDTO = new MovieWithActorsDTO(movie.getId(), movie.getTitle(), movie.getRate(), movie.getSynopsis());
        List<ActorMiniDTO> actorMiniDTOS = new ArrayList<>();
        Hibernate.initialize(movie.getActors());
        for (Actor actor : movie.getActors()) {
            actorMiniDTOS.add(ActorMapper.actorToActorMiniDTO(actor));
        }
        movieWithActorsDTO.setActors(actorMiniDTOS);
        return movieWithActorsDTO;
    }

    public static Movie movieWithActorsDTOtoMovie(MovieWithActorsDTO movieWithActorsDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieWithActorsDTO.getTitle());
        movie.setSynopsis(movieWithActorsDTO.getSynopsis());
        movie.setRate(movieWithActorsDTO.getRate());
        List<ActorMiniDTO> actorsDto = movieWithActorsDTO.getActors();
        List<Actor> actors = new ArrayList<>();
        for (ActorMiniDTO actorDto : actorsDto) {
            Actor a = ActorMapper.actorMiniDTOToActor(actorDto);
            actors.add(a);
        }
        movie.setActors(actors);
        return movie;
    }
}
