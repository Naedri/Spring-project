package com.projetspring.projet.service;

import com.projetspring.projet.entities.Actor;
import com.projetspring.projet.entities.Movie;
import com.projetspring.projet.exceptions.MovieCreationWithoutActorsException;
import com.projetspring.projet.exceptions.NoneExistantActorException;
import com.projetspring.projet.responses.ActorMiniDTO;
import com.projetspring.projet.responses.MovieWithActorsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)

/**
 * @link https://reflectoring.io/spring-boot-data-jpa-test/
 */
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class, Service.class}))
public class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    private MovieWithActorsDTO movieWithActorsDTO;
    private ActorMiniDTO actorMiniDTO;

    private final Long id = null;
    private final String title = "title";
    private final Float rate = 5f;
    private final String synopsis = "synopsis";

    private final String firstname = "firstname";
    private final String lastname = "lastname";

    @BeforeEach
    void init() {
        movieWithActorsDTO = new MovieWithActorsDTO(id, title, rate, synopsis);
        actorMiniDTO = new ActorMiniDTO(id, firstname, lastname);
        movieWithActorsDTO.setActors(Arrays.asList(actorMiniDTO));
    }

    @Test
    void addMovie() throws MovieCreationWithoutActorsException, NoneExistantActorException {
        MovieWithActorsDTO movieWithActorsDTO1 = movieService.addMovie(movieWithActorsDTO);
        Assertions.assertNotNull(movieWithActorsDTO1.getId());
        Assertions.assertEquals(movieWithActorsDTO.getTitle(), movieWithActorsDTO1.getTitle());
        Assertions.assertEquals(movieWithActorsDTO.getRate(), movieWithActorsDTO1.getRate());
        Assertions.assertEquals(movieWithActorsDTO.getSynopsis(), movieWithActorsDTO1.getSynopsis());
        ActorMiniDTO actor = movieWithActorsDTO1.getActors().get(0);
        Assertions.assertNotNull(actor.getId());
        Assertions.assertEquals(movieWithActorsDTO.getActors().get(0).getFirstName(), actor.getFirstName());
        Assertions.assertEquals(movieWithActorsDTO.getActors().get(0).getLastName(), actor.getLastName());
    }

    @Test
    void addMovieThrowMovieCreationWithoutActorsException() {
        movieWithActorsDTO.setActors(new ArrayList<>());
        Assertions.assertThrows(MovieCreationWithoutActorsException.class, () -> {
            movieService.addMovie(movieWithActorsDTO);
        });
    }

    @Test
    void addMovieThrowNoneExistantActorException() {
        movieWithActorsDTO.getActors().get(0).setId(1L);
        Assertions.assertThrows(NoneExistantActorException.class, () -> {
            movieService.addMovie(movieWithActorsDTO);
        });
    }

    @Test
    void findAll() throws MovieCreationWithoutActorsException, NoneExistantActorException {
        MovieWithActorsDTO movieWithActorsDTO1 = new MovieWithActorsDTO(null, "movieWithActorsDTO1", 0f, "synopsis");
        ActorMiniDTO actor = new ActorMiniDTO(null, "Jean", "Dujardin");
        movieWithActorsDTO1.setActors(Arrays.asList(actor));

        movieService.addMovie(movieWithActorsDTO);
        movieService.addMovie(movieWithActorsDTO1);

        List<MovieWithActorsDTO> movieList = movieService.findAll();
        Assertions.assertEquals(2, movieList.size());

        movieWithActorsDTO.setActors(null);
        movieList.get(0).setActors(null);
        movieWithActorsDTO1.setActors(null);
        movieList.get(1).setActors(null);
        Assertions.assertEquals(movieWithActorsDTO, movieList.get(0));
        Assertions.assertEquals(movieWithActorsDTO1, movieList.get(1));
    }
}
