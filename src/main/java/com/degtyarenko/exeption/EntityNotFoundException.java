package com.degtyarenko.exeption;

import static com.degtyarenko.constant.ExceptionHandlerConstant.ENTITY_IS_NOT_FOUND_ID;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super(ENTITY_IS_NOT_FOUND_ID + id);
    }

}
