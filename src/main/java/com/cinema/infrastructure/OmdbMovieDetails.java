package com.cinema.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbMovieDetails {
    @JsonProperty(value = "Title")
    public String title;
    @JsonProperty(value = "Year")
    public String year;
    @JsonProperty(value = "Rated")
    public String rated;
    @JsonProperty(value = "Released")
    public String released;
    @JsonProperty(value = "Runtime")
    public String runtime;
    @JsonProperty(value = "Genre")
    public String genre;
    @JsonProperty(value = "Director")
    public String director;
    @JsonProperty(value = "Writer")
    public String writer;
    @JsonProperty(value = "Actors")
    public String actors;
    @JsonProperty(value = "Plot")
    public String plot;
    @JsonProperty(value = "Language")
    public String language;
    @JsonProperty(value = "Country")
    public String country;
    @JsonProperty(value = "Awards")
    public String awards;
    @JsonProperty(value = "Poster")
    public String poster;
    @JsonProperty(value = "Ratings")
    public MovieRating[] ratings;
    @JsonProperty(value = "Metascore")
    public String metascore;
    @JsonProperty(value = "imdbRating")
    public String imdbRating;
    @JsonProperty(value = "imdbVotes")
    public String imdbVotes;
    @JsonProperty(value = "imdbID")
    public String imdbId;
    @JsonProperty(value = "Type")
    public String type;
    @JsonProperty(value = "DVD")
    public String dvd;
    @JsonProperty(value = "BoxOffice")
    public String boxOffice;
    @JsonProperty(value = "Production")
    public String production;
    @JsonProperty(value = "Website")
    public String website;
    @JsonProperty(value = "Response")
    public String response;
}

