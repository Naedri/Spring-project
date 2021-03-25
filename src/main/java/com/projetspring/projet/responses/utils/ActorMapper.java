package com.projetspring.projet.responses.utils;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.responses.ActorMiniDTO;

public class ActorMapper {
    public static final ActorMiniDTO actorToActorMiniDTO(Actor actor) {
        ActorMiniDTO actorMiniDTO = new ActorMiniDTO(actor.getId(), actor.getFirstName(), actor.getLastName());
        return actorMiniDTO;
    }

    public static Actor actorMiniDTOToActor(ActorMiniDTO actorMiniDTO){
        Actor a = new Actor();
        System.out.println(actorMiniDTO);
        a.setFirstName(actorMiniDTO.getFirstName());
        a.setLastName(actorMiniDTO.getLastName());
        System.out.println(actorMiniDTO.getId());
        if (actorMiniDTO.getId() != null)
            a.setId(actorMiniDTO.getId());
        System.out.println(a);
        return a;
    }
}
