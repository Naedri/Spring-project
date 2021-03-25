package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.exceptions.NoneExistantActorException;
import com.projetspring.projet.repositories.ActorRepository;
import com.projetspring.projet.responses.ActorWithMoviesDTO;
import com.projetspring.projet.responses.MovieWithActorsDTO;
import com.projetspring.projet.responses.utils.ActorMapper;
import com.projetspring.projet.responses.utils.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    public List<ActorWithMoviesDTO> findAllActors() {
        List<Actor> actors = actorRepository.findAllByJPQL();
        List<ActorWithMoviesDTO> actorWithMoviesDTOS = new ArrayList<>();
        for (Actor actor : actors) {
            actorWithMoviesDTOS.add(ActorMapper.actorToActorWithMoviesDTO(actor));
        }
        return actorWithMoviesDTOS;
    }

    public ActorWithMoviesDTO findByFullName(String firstName, String lastName) throws NoneExistantActorException {
        Actor actor = actorRepository.findByJPQL(firstName, lastName);
        if (actor == null)
            throw new NoneExistantActorException("Actor does not exist");
        return ActorMapper.actorToActorWithMoviesDTO(actor);
    }
}
