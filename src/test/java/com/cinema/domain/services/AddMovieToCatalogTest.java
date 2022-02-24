package com.cinema.domain.services;

import com.cinema.domain.entities.Movie;
import com.cinema.domain.repositories.MovieRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;

class AddMovieToCatalogTest {

    @Test
    void add() {

        Integer movieId = Integer.valueOf(123456);
        String movieTitle = "Some movie";
        String imdbId = "someImdbId";

        Movie movie = new Movie(movieTitle, imdbId, Duration.ofSeconds(10));

        Movie movieMock = Mockito.mock(Movie.class);
        Mockito.when(movieMock.getId()).thenReturn(movieId);

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.save(movie)).thenReturn(movieMock);

        AddMovieToCatalog addMovieToCatalog = new AddMovieToCatalog(movieRepository);
        Integer id = addMovieToCatalog.Add(movieTitle, imdbId, Duration.ofSeconds(10));

        Assert.assertEquals("The movie was added to the catalog", movieId, id);
    }
}