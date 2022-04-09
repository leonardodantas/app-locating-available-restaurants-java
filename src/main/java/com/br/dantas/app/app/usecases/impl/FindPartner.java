package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.exceptions.ErrorsException;
import com.br.dantas.app.app.exceptions.PartnerNotFound;
import com.br.dantas.app.app.models.IDocument;
import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.IFIndPartner;
import com.br.dantas.app.domain.Partner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FindPartner implements IFIndPartner {

    private final IPartnerRepository partnerRepository;

    public FindPartner(@Qualifier("mongoRepository")final IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Partner byId(final String id) {
        return partnerRepository.findById(id)
                .orElseThrow(() -> new PartnerNotFound(ErrorsException.PARTNER_ID_NOT_FOUND.getMessage()));
    }

    @Override
    public Partner byDocument(final IDocument document) {
        return partnerRepository.findByDocument(document.getDocumentOnlyNumber())
                .orElseThrow(() -> new PartnerNotFound(ErrorsException.PARTNER_DOCUMENT_NOT_FOUND.getMessage()));
    }
}
