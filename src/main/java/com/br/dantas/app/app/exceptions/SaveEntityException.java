package com.br.dantas.app.app.exceptions;

public class SaveEntityException extends RuntimeException{

    public SaveEntityException(final String message){
        throw new RuntimeException(message);
    }
}
