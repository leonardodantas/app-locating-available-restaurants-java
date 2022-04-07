package com.br.dantas.app.infra.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DocumentValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DocumentValid {
    public String message() default "Documento não pode ter sido cadastrado anteriormente";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
