package com.cinema.domain.services;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(Integer movieId) {
        super("movie not found for id " + movieId);
    }
}
