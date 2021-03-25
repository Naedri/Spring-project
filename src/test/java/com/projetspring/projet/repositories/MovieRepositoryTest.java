package com.projetspring.projet.repositories;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

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
        Movie movieWithActorsDTO2 = new Movie(null, "title2", 0f, "synopsis", null);
        Actor actorMiniDTO = new Actor(null, "Jean", "Dujardin", Arrays.asList(movieWithActorsDTO));
        movieWithActorsDTO.setActors(Arrays.asList(actorMiniDTO));
        movieWithActorsDTO2.setActors(Arrays.asList(actorMiniDTO));

        actorRepository.save(actorMiniDTO);
        movieRepository.save(movieWithActorsDTO);
        movieRepository.save(movieWithActorsDTO2);

        List<Movie> movieList = movieRepository.getAllByJPQL();
        Assertions.assertEquals(2, movieList.size());
        Assertions.assertEquals(movieWithActorsDTO, movieList.get(0));
        Assertions.assertEquals(movieWithActorsDTO2, movieList.get(1));
    }
}