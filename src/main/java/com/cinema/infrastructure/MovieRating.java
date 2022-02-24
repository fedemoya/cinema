package com.cinema.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieRating {
    @JsonProperty(value = "Source")
    public String source;
    @JsonProperty(value = "Value")
    public String value;
}
