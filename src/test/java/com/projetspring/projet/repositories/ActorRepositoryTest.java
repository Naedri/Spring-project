package com.projetspring.projet.repositories;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;


@ExtendWith(SpringExtension.class)

/**
 * @link https://reflectoring.io/spring-boot-data-jpa-test/
 */
@DataJpaTest
class ActorRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void findActorJPQLTest() {
        String surname1 = "Jean";
        String lastname1 = "Dujardin";

        String surname2 = "Pierre";
        String lastname2 = "Dujardin";

        Movie movieWithActorsDTO1 = new Movie(null, "title", 0f, "synopsis", null);
        Movie movieWithActorsDTO2 = new Movie(null, "title2", 0f, "synopsis", null);
        Actor actorMiniDTO1 = new Actor(null, surname1, lastname1, Collections.singletonList(movieWithActorsDTO1));
        Actor actorMiniDTO2 = new Actor(null, surname2, lastname2, Collections.singletonList(movieWithActorsDTO1));

        movieWithActorsDTO1.setActors(Collections.singletonList(actorMiniDTO1));
        movieWithActorsDTO2.setActors(Collections.singletonList(actorMiniDTO2));

        actorRepository.save(actorMiniDTO1);
        actorRepository.save(actorMiniDTO2);
        movieRepository.save(movieWithActorsDTO1);
        movieRepository.save(movieWithActorsDTO2);

        Actor actorFetched = actorRepository.findByJPQL(surname1, lastname1);
        Assertions.assertEquals(surname1, actorFetched.getFirstName());
        Assertions.assertEquals(lastname1, actorFetched.getLastName());

        actorFetched = actorRepository.findByJPQL(surname2, lastname2);
        Assertions.assertEquals(surname2, actorFetched.getFirstName());
        Assertions.assertEquals(lastname2, actorFetched.getLastName());
    }
}