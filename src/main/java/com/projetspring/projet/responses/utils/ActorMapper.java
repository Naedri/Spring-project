package com.projetspring.projet.responses.utils;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.responses.ActorMiniDTO;

public class ActorMapper {
    public static final ActorMiniDTO actorToActorMiniDTO(Actor actor) {
        ActorMiniDTO actorMiniDTO = new ActorMiniDTO(actor.getId(), actor.getFirstName(), actor.getLastName());
        return actorMiniDTO;
    }
}
