package com.br.dantas.app.infra.controllers.advice;

import com.br.dantas.app.app.exceptions.FindPartnerWithAddressAndCoverageAreaException;
import com.br.dantas.app.app.exceptions.InitialIndexUnavailableException;
import com.br.dantas.app.app.exceptions.PartnerNotFound;
import com.br.dantas.app.app.exceptions.SaveEntityException;
import com.br.dantas.app.infra.controllers.advice.response.ErrorResponse;
import com.br.dantas.app.infra.controllers.advice.response.MessageError;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    private final MessageSource messageSource;

    public AdviceController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = PartnerNotFound.class)
    public ResponseEntity<?> partnerNotFoundExceptionHandler(final PartnerNotFound error) {
        final var message = MessageError.from(error);
        final var response = ErrorResponse.of(HttpStatus.NOT_FOUND, message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InitialIndexUnavailableException.class)
    public ResponseEntity<?> initialIndexUnavailableExceptionHandler(final InitialIndexUnavailableException error) {
        final var message = MessageError.from(error);
        final var response = ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> initialIndexUnavailableExceptionHandler(final MethodArgumentNotValidException error) {
        final var fields = error.getBindingResult().getFieldErrors();

        final var errors = fields.stream()
                .map(field -> MessageError.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale()))).toList();

        final var response = ErrorResponse.of(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(final RuntimeException error) {
        final var message = MessageError.from(error);
        final var response = ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SaveEntityException.class)
    public ResponseEntity<?> errorSavingObjectExceptionHandler(final SaveEntityException error) {
        final var message = MessageError.from(error);
        final var response = ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FindPartnerWithAddressAndCoverageAreaException.class)
    public ResponseEntity<?> findPartnerWithAddressAndCoverageAreaExceptionHandler(final FindPartnerWithAddressAndCoverageAreaException error) {
        final var message = MessageError.from(error);
        final var response = ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
