package com.cinema.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OmdbClientImpl implements OmdbClient {

    private String apikey;

    public OmdbClientImpl(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public OmdbMovieDetails FetchMovieDetails(String movieImdbId) throws OmdbClientException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().
                scheme("http").
                host("www.omdbapi.com").
                queryParam("apikey", this.apikey).
                queryParam("i", movieImdbId).build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uriComponents.toUri()).GET().build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new OmdbClientException(e);
        } catch (InterruptedException e) {
            throw new OmdbClientException(e);
        }
        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            OmdbMovieDetails omdbMovieDetails = null;
            try {
                omdbMovieDetails = mapper.readValue(response.body(), OmdbMovieDetails.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return omdbMovieDetails;
        } else {
            throw new OmdbClientException("failed retrieving details for movie id " + movieImdbId);
        }
    }
}
