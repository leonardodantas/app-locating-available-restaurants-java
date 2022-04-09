package com.br.dantas.app.infra.controllers.json.request;

import com.br.dantas.app.app.models.IDocument;

import javax.validation.constraints.NotBlank;

public record DocumentRequesJson(
        @NotBlank
        String document) implements IDocument {
}
