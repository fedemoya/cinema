package com.cinema.domain.services;

import com.cinema.domain.entities.Movie;
import com.cinema.domain.repositories.MovieRepository;
import com.cinema.domain.entities.MovieSchedule;
import com.cinema.domain.repositories.MovieScheduleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddMovieScheduleTest {

    @Test
    void add() {

        Integer movieId = Integer.valueOf(123456);
        String movieTitle = "Some movie";
        String imdbId = "someImdbId";
        Instant dateTime = Instant.now();
        Double price = 2.5;

        Movie movie = new Movie(movieTitle, imdbId, Duration.ofSeconds(10));

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        MovieScheduleRepository movieScheduleRepository = Mockito.mock(MovieScheduleRepository.class);
        Mockito.
                when(movieScheduleRepository.findMoviesScheduledBefore(dateTime)).
                thenReturn(new ArrayList<>());
        Mockito.
                when(movieScheduleRepository.findMoviesScheduledAfter(dateTime)).
                thenReturn(new ArrayList<>());

        Mockito.when(movieScheduleRepository.
                save(new MovieSchedule(movie, dateTime, price))).
                thenReturn(new MovieSchedule(movie, dateTime, price));

        AddMovieSchedule addMovieScheduleService = new AddMovieSchedule(movieRepository, movieScheduleRepository);

        assertDoesNotThrow(() -> addMovieScheduleService.Add(movieId, dateTime, 2.5));
        Mockito.verify(movieScheduleRepository).findMoviesScheduledBefore(dateTime);
        Mockito.verify(movieScheduleRepository).findMoviesScheduledAfter(dateTime);
        Mockito.verify(movieScheduleRepository).save(new MovieSchedule(movie, dateTime, price));
    }

    @Test
    void addButNotAvailable() {

        Integer movieId = Integer.valueOf(123456);
        String movieTitle = "Some movie";
        String imdbId = "someImdbId";
        Instant dateTime = Instant.now();
        Double price = 2.5;

        Movie movie = new Movie(movieTitle, imdbId, Duration.ofSeconds(10));

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie beforeMovie = new Movie("Other movie", "otherImdbId", Duration.ofSeconds(10));
        MovieSchedule beforeMovieSchedule = new MovieSchedule(beforeMovie, Instant.now().minusSeconds(5), 2.5);
        ArrayList<MovieSchedule> beforeMovieSchedules = new ArrayList<>();
        beforeMovieSchedules.add(beforeMovieSchedule);
        MovieScheduleRepository movieScheduleRepository = Mockito.mock(MovieScheduleRepository.class);
        Mockito.
                when(movieScheduleRepository.findMoviesScheduledBefore(dateTime)).
                thenReturn(beforeMovieSchedules);
        Mockito.
                when(movieScheduleRepository.findMoviesScheduledAfter(dateTime)).
                thenReturn(new ArrayList<>());

        MovieSchedule movieSchedule = new MovieSchedule(movie, dateTime, price);
        Mockito.when(movieScheduleRepository.save(movieSchedule)).thenReturn(movieSchedule);

        AddMovieSchedule addMovieScheduleService = new AddMovieSchedule(movieRepository, movieScheduleRepository);

        assertThrows(NotAvailableException.class, () -> addMovieScheduleService.Add(movieId, dateTime, 2.5));
    }
}