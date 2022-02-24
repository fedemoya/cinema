package com.cinema.domain.services;

import com.cinema.domain.entities.Movie;
import com.cinema.domain.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public class AddMovieToCatalog {

    private MovieRepository movieRepository;

    @Autowired
    public AddMovieToCatalog(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Adds a new movie to the movies catalog
     *
     * @param title
     * @param imdbId
     * @return The MovieCatalog database id
     */
    public Integer Add(String title, String imdbId, Duration runtime) {
        Movie movie = movieRepository.save(new Movie(title, imdbId, runtime));
        return movie.getId();
    }
}
