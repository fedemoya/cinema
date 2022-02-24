package com.cinema.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;
import java.util.Objects;

@Entity
public class MovieSchedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Instant dateTime;

    @Positive
    private Double price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public MovieSchedule() {
    }

    public MovieSchedule(Movie movie, Instant dateTime, Double price) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public Double getPrice() {
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieSchedule that = (MovieSchedule) o;
        return Objects.equals(id, that.id) && dateTime.equals(that.dateTime) && price.equals(that.price) && movie.equals(that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, price, movie);
    }
}
