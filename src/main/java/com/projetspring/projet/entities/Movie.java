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
@Table(name = "movies")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float rate;

    private String synopsis;

//    @ToString.Exclude
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, targetEntity = Actor.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "plays",
//            joinColumns = {
//                    @JoinColumn(name = "movie_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "actor_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)})
//    private List<Actor> actors;

    @ManyToMany(targetEntity = Actor.class, fetch = FetchType.LAZY)
    @JoinTable(name = "plays",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "actor_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Actor> actors = new HashSet<>();

    public void addActor(Actor actor){
        actors.add(actor);
        actor.getMovies().add(this);
    }

    public void removeActor(Actor actor){
        actors.remove(actor);
        actor.getMovies().remove(this);
    }
}
