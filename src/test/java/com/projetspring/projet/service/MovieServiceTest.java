package com.projetspring.projet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)

/**
 * @link https://reflectoring.io/spring-boot-data-jpa-test/
 */
@DataJpaTest
public class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    @Test
    void deleteMovieTest() {
    }

    @Test
    void addMovie() {
    }

    @Test
    void findAll() {
    }
}
