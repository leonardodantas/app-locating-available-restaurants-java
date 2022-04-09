package com.br.dantas.app.app.usecases;

import com.br.dantas.app.app.models.IPartner;

public interface FormatDocumentNumber {

    static String getDocumentOnlyNumbers(IPartner partner){
        return partner.document().replace(".", "").replace("/", "").replace("-", "");
    }

    static String getDocumentOnlyNumbers(String document) {
        return document.replace(".", "").replace("/", "").replace("-", "");
    }
}
