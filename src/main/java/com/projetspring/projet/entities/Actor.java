package com.projetspring.projet.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "actors")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToMany(mappedBy = "actors", targetEntity = Movie.class, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
//    private List<Movie> movies;
//
    @ManyToMany(targetEntity = Movie.class, mappedBy = "actors", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();

    public void addMovie(Movie movie){
        movies.add(movie);
        movie.getActors().add(this);
    }

    public void removeMovie(Movie movie){
        movies.remove(movie);
        movie.getActors().remove(this);
    }
}
