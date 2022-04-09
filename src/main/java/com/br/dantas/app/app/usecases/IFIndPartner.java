package com.br.dantas.app.app.usecases;

import com.br.dantas.app.app.models.IDocument;
import com.br.dantas.app.domain.Partner;

public interface IFIndPartner {

    Partner byId(String id);
    Partner byDocument(IDocument document);

}
