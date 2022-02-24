package com.cinema.domain.services;

import com.cinema.domain.entities.Movie;
import com.cinema.domain.entities.MovieSchedule;
import com.cinema.domain.repositories.MovieRepository;
import com.cinema.domain.repositories.MovieScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class GetMovieSchedules {

    private MovieRepository movieRepository;
    private MovieScheduleRepository movieScheduleRepository;

    @Autowired
    public GetMovieSchedules(MovieRepository movieRepository, MovieScheduleRepository movieScheduleRepository) {
        this.movieRepository = movieRepository;
        this.movieScheduleRepository = movieScheduleRepository;
    }

    public List<MovieSchedule> get(Integer movieId) throws MovieNotFoundException {
        Optional<Movie> movieOpt = this.movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) {
            throw new MovieNotFoundException(movieId);
        }
        return this.movieScheduleRepository.findNewSchedulesForMovie(movieId, Instant.now());
    }
}
