package com.cinema.domain.services;

import java.time.Instant;

public class NotAvailableException extends Exception {
    public NotAvailableException(Instant time) {
        super("the cinema is not available at " + time);
    }
}
