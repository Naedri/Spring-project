package com.projetspring.projet.repository;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    default void addActor(Actor actor) {
        save(actor);
    }
}
