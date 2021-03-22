package com.projetspring.projet.controller;


import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private ActorService actorService;

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
