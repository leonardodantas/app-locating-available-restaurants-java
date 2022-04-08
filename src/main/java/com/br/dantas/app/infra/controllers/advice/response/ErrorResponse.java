package com.br.dantas.app.infra.controllers.advice.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public record ErrorResponse(String uuid, HttpStatus httpStatus,
                            LocalDateTime date, Collection<MessageError> errors) {

    public static ErrorResponse of(final HttpStatus httpStatus, final MessageError message) {
        return new ErrorResponse(UUID.randomUUID().toString(), httpStatus, LocalDateTime.now(), Collections.singletonList(message));
    }

    public static ErrorResponse of(final HttpStatus httpStatus, final Collection<MessageError> message) {
        return new ErrorResponse(UUID.randomUUID().toString(), httpStatus, LocalDateTime.now(), message);
    }

}
