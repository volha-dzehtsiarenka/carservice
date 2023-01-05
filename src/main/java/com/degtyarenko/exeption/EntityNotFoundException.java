package com.degtyarenko.exeption;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Entity is not found, id = " + id);
    }

}
