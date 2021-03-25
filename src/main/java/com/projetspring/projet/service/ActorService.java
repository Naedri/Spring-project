package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.exceptions.NoneExistantActorException;
import com.projetspring.projet.repositories.ActorRepository;
import com.projetspring.projet.responses.ActorWithMoviesDTO;
import com.projetspring.projet.responses.utils.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ActorWithMoviesDTO findByFullName(String firstName, String lastName) {
        Actor actor = actorRepository.findByJPQL(firstName, lastName);
        if (actor == null)
            throw new NoneExistantActorException("Actor does not exist");
        return ActorMapper.actorToActorWithMoviesDTO(actor);
    }

    public ActorWithMoviesDTO findById(Long actorId) throws NoneExistantActorException {
        Actor actor = actorRepository.findByIdJPQL(actorId);
        if (actor == null)
            throw new NoneExistantActorException("Actor does not exist");
        return ActorMapper.actorToActorWithMoviesDTO(actor);
    }
}
