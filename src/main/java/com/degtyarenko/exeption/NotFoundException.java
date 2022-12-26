package com.degtyarenko.exeption;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Entity is not found, id = "+id);
    }
}
