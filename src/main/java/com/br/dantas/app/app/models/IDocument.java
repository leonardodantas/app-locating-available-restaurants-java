package com.br.dantas.app.app.models;

import com.br.dantas.app.app.usecases.FormatDocumentNumber;

public interface IDocument {

    String document();

    default String getDocumentOnlyNumber(){
        return FormatDocumentNumber.getDocumentOnlyNumbers(this.document());
    }
}
