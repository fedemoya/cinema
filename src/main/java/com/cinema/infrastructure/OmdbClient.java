package com.cinema.infrastructure;

public interface OmdbClient {
    OmdbMovieDetails FetchMovieDetails(String movieId) throws OmdbClientException;
}
