package com.cinema.domain.repositories;

import com.cinema.domain.entities.Movie;
import com.cinema.domain.entities.MovieSchedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MovieScheduleRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieScheduleRepository movieScheduleRepository;

    @Test
    void findTodayMoviesScheduledBeforeAfter() {

        Movie movie1 = new Movie("movie1", "123abc", Duration.ofSeconds(10));
        movie1 = movieRepository.save(movie1);
        movieScheduleRepository.save(new MovieSchedule(
                movie1,
                Instant.now().minusSeconds(60),
                2.5
        ));
        Movie movie2 = new Movie("movie2", "456abc", Duration.ofSeconds(10));
        movie2 = movieRepository.save(movie2);
        movieScheduleRepository.save(new MovieSchedule(
                movie2,
                Instant.now().minusSeconds(30),
                2.5
        ));
        Movie movie3 = new Movie("movie3", "789abc", Duration.ofSeconds(10));
        movie3 = movieRepository.save(movie3);
        movieScheduleRepository.save(new MovieSchedule(
                movie3,
                Instant.now().plusSeconds(30),
                2.5
        ));

        List<MovieSchedule> schedulesBefore = movieScheduleRepository.findMoviesScheduledBefore(Instant.now());

        assertEquals(2, schedulesBefore.size());
        assertEquals(movie1.getTitle(), schedulesBefore.get(1).getMovie().getTitle());
        assertEquals(movie2.getTitle(), schedulesBefore.get(0).getMovie().getTitle(), movie2.getTitle());

        List<MovieSchedule> schedulesAfter = movieScheduleRepository.findMoviesScheduledAfter(Instant.now());

        assertEquals(1, schedulesAfter.size());
        assertEquals(movie3.getTitle(), schedulesAfter.get(0).getMovie().getTitle());
    }

    @Test
    void findNewSchedulesForMovie() {
        Movie movie = new Movie("movie1", "456hjk", Duration.ofSeconds(10));
        movie = movieRepository.save(movie);
        movieScheduleRepository.save(new MovieSchedule(
                movie,
                Instant.now().minusSeconds(60),
                3.0
        ));
        MovieSchedule schedule = movieScheduleRepository.save(new MovieSchedule(
                movie,
                Instant.now().plusSeconds(30),
                2.5
        ));
        Movie otherMovie = new Movie("movie2", "456poi", Duration.ofSeconds(10));
        otherMovie = movieRepository.save(otherMovie);
        movieScheduleRepository.save(new MovieSchedule(
                otherMovie,
                Instant.now().plusSeconds(30),
                2.5
        ));
        List<MovieSchedule> schedules = movieScheduleRepository.findNewSchedulesForMovie(movie.getId(), Instant.now());
        assertEquals(1, schedules.size());
        assertEquals(schedule, schedules.get(0));
    }
}
