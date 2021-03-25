package com.projetspring.projet.controllers;


import com.projetspring.projet.exceptions.NoneExistantActorException;
import com.projetspring.projet.responses.ActorWithMoviesDTO;
import com.projetspring.projet.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actor")
    public ResponseEntity<ActorWithMoviesDTO> findActorByFullName(@RequestParam String firstName, @RequestParam String lastName){
        ActorWithMoviesDTO actor;
        try {
            actor = actorService.findByFullName(firstName, lastName);
        } catch (NoneExistantActorException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actor);
    }

    @GetMapping("")
    public ResponseEntity<List<ActorWithMoviesDTO>> findAllActors(){
        return ResponseEntity.ok(actorService.findAllActors());
    }
}
