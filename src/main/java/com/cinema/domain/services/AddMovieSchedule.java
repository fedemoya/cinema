package com.cinema.domain.services;

import com.cinema.domain.entities.Movie;
import com.cinema.domain.repositories.MovieRepository;
import com.cinema.domain.entities.MovieSchedule;
import com.cinema.domain.repositories.MovieScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class AddMovieSchedule {

    private MovieRepository movieRepository;
    private MovieScheduleRepository movieScheduleRepository;

    @Autowired
    public AddMovieSchedule(MovieRepository movieRepository, MovieScheduleRepository movieScheduleRepository) {
        this.movieRepository = movieRepository;
        this.movieScheduleRepository = movieScheduleRepository;
    }

    public void Add(Integer movieId, Instant dateTime, Double price) throws MovieNotFoundException, NotAvailableException {

        Optional<Movie> movieOpt = this.movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) {
            throw new MovieNotFoundException(movieId);
        }

        Movie movie = movieOpt.get();

        checkAvailability(movie, dateTime);

        movieScheduleRepository.save(new MovieSchedule(movie, dateTime, price));
    }

    private void checkAvailability(Movie movie, Instant dateTime) throws NotAvailableException {

        List<MovieSchedule> moviesBefore = movieScheduleRepository.findMoviesScheduledBefore(dateTime);

        if (!moviesBefore.isEmpty()) {

            MovieSchedule movieScheduleBefore = moviesBefore.get(0);
            Movie movieBefore = movieScheduleBefore.getMovie();
            if (movieScheduleBefore.getDateTime().getEpochSecond() + movieBefore.getRuntime().getSeconds() > dateTime.getEpochSecond()) {
                throw new NotAvailableException(dateTime);
            }
        }

        List<MovieSchedule> moviesAfter = movieScheduleRepository.findMoviesScheduledAfter(dateTime);

        if (!moviesAfter.isEmpty()) {

            MovieSchedule movieScheduleAfter = moviesAfter.get(0);
            if (dateTime.getEpochSecond() + movie.getRuntime().getSeconds() > movieScheduleAfter.getDateTime().getEpochSecond()) {
                throw new NotAvailableException(dateTime);
            }
        }
    }
}
