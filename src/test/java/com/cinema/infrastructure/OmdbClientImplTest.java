package com.cinema.infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OmdbClientImplTest {


    @Test
    // TODO: simulate server using https://docs.hoverfly.io/projects/hoverfly-java/en/latest/
    void fetchMovieDetails() {

        OmdbClient omdbClient = new OmdbClientImpl(System.getenv("OMDB_API_KEY"));
        OmdbMovieDetails omdbMovieDetails = null;
        try {
            omdbMovieDetails = omdbClient.FetchMovieDetails("tt0232500");
        } catch (OmdbClientException e) {
            e.printStackTrace();
        }
        assertEquals("The Fast and the Furious", omdbMovieDetails.title);
    }
}