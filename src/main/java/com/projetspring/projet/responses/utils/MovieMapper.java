package com.projetspring.projet.responses.utils;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.responses.ActorMiniDTO;
import com.projetspring.projet.responses.MovieWithActorsDTO;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {
    public static final MovieWithActorsDTO movieToMovieWithActorsDTP(Movie movie) {
        System.out.println("test"+movie);
        MovieWithActorsDTO movieWithActorsDTO = new MovieWithActorsDTO(movie.getId(), movie.getTitle(), movie.getRate(), movie.getSynopsis());
        List<ActorMiniDTO> actorMiniDTOS = new ArrayList<>();
        Hibernate.initialize(movie.getActors());
        for (Actor actor : movie.getActors()) {
            System.out.println(actor);
            actorMiniDTOS.add(ActorMapper.actorToActorMiniDTO(actor));
        }
        movieWithActorsDTO.setActors(actorMiniDTOS);
        return movieWithActorsDTO;
    }
}
