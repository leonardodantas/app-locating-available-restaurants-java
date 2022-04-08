package com.br.dantas.app.app.exceptions;

public enum ErrorsException {

    PARTNER_ID_NOT_FOUND("Partner id not found"),
    PARTNER_DOCUMENT_NOT_FOUND("Partner document not found"),
    NO_PARTNERS_FOUND_FOR_THE_CURRENT_AREA("No partners found for the current area");

    private final String message;

    ErrorsException(final String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
