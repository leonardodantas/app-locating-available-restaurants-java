package com.br.dantas.app.app.exceptions;

public class InitialIndexUnavailableException extends RuntimeException{

    private final static String message = "Initial index unavailable";

    public InitialIndexUnavailableException(){
        super(message);
    }
}
