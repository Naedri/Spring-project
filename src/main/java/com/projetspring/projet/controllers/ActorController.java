package com.projetspring.projet.controllers;


import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("")
    public ResponseEntity<List<Actor>> findAllActors() {
        List<Actor> actors = actorService.findAllActors();
        return ResponseEntity.ok(actors);
    }
}
