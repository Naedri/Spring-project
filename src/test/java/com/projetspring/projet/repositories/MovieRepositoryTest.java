package com.projetspring.projet.repositories;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)

/**
 * @link https://reflectoring.io/spring-boot-data-jpa-test/
 */
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void getAllByJPQLTest() {
        Movie movieWithActorsDTO = new Movie(null, "title", 0f, "synopsis", null);
        Actor actorMiniDTO = new Actor(null, "firstname", "lastname", Arrays.asList(movieWithActorsDTO));
        movieWithActorsDTO.setActors(Arrays.asList(actorMiniDTO));
        actorRepository.save(actorMiniDTO);
        movieRepository.save(movieWithActorsDTO);
        movieRepository.getAllByJPQL();
    }
}