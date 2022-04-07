package com.br.dantas.app.infra.controllers.advice.response;

import org.springframework.validation.FieldError;

public record MessageError(String message) {

    public static MessageError from(final RuntimeException error) {
        return new MessageError(error.getMessage());
    }

    public static MessageError of(FieldError field, String message) {
        return new MessageError(field.getField().concat(" - ".concat(message)));
    }
}
