package com.degtyarenko.exeption;

public class EntityIsUsedException extends RuntimeException{

    public EntityIsUsedException(String message){
        super(message);
    }

    public EntityIsUsedException(){
        super("Entity already exists");
    }

}
