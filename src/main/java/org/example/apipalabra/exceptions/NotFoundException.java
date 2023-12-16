package org.example.apipalabra.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String thingSearching, Integer id) {
        super(thingSearching + " not found with id " + id);
    }
}
