package com.cinema.infrastructure;

public class OmdbClientException extends Exception {

    public OmdbClientException(String message) {
        super(message);
    }

    public OmdbClientException(Throwable cause) {
        super(cause);
    }
}
