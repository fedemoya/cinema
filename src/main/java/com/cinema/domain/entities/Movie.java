package com.cinema.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Objects;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String title;

    @Column(unique = true)
    private String imdbId;

    @NotNull
    private Duration runtime;

    public Movie() {
    }

    public Movie(String title, String imdbId, Duration runtime) {

        this.title = title;
        this.imdbId = imdbId;
        this.runtime = runtime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public Duration getRuntime() { return runtime; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && title.equals(movie.title) && imdbId.equals(movie.imdbId) && runtime.equals(movie.runtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imdbId, runtime);
    }
}
