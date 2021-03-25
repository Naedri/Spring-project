package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.repositories.ActorRepository;
import com.projetspring.projet.responses.ActorWithMoviesDTO;
import com.projetspring.projet.responses.utils.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return ActorMapper.actorToActorWithMoviesDTO(actor);
    }
}
