package com.br.dantas.app.infra.annotations;

import com.br.dantas.app.app.validators.IDocumentValidator;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DocumentValidatorAnnotation implements ConstraintValidator<DocumentValid, String> {

    private final IDocumentValidator documentValidator;

    public DocumentValidatorAnnotation(final IDocumentValidator documentValidator) {
        this.documentValidator = documentValidator;
    }

    @Override
    public boolean isValid(final String value,final ConstraintValidatorContext context) {
        if(Strings.isNullOrEmpty(value)) {
            return false;
        }
        return documentValidator.canBeRegistered(value);
    }
}
