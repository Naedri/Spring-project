package com.projetspring.projet.controllers;


import com.projetspring.projet.responses.ActorWithMoviesDTO;
import com.projetspring.projet.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actor")
    public ResponseEntity<ActorWithMoviesDTO> findActorByFullName(@RequestParam String firstName, @RequestParam String lastName){
        ActorWithMoviesDTO actor = actorService.findByFullName(firstName, lastName);
        return ResponseEntity.ok(actor);
    }
}
