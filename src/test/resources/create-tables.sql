CREATE TABLE Actors(
    id VARCHAR PRIMARY KEY,
    firstName VARCHAR NOT NULL,
    lastName VARCHAR NOT NULL
);

CREATE TABLE Movies(
   id VARCHAR PRIMARY KEY,
   title VARCHAR NOT NULL,
   rate FLOAT NOT NULL,
   synopsis VARCHAR NOT NULL
);

CREATE TABLE Plays
(
    movieId VARCHAR,
    actorId VARCHAR,
    CONSTRAINT movie_cat_pk PRIMARY KEY (movie_id, actor_id),
    CONSTRAINT FK_movies FOREIGN KEY (movie_id) REFERENCES Movies (id),
    CONSTRAINT FK_actors FOREIGN KEY (actor_id) REFERENCES Actors (id)
);

