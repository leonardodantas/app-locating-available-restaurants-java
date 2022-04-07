package com.br.dantas.app.app.validators;

import com.br.dantas.app.app.repository.IPartnerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DocumentValidator implements IDocumentValidator{

    private final IPartnerRepository partnerRepository;

    public DocumentValidator(@Qualifier("mongoRepository")final IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public boolean canBeRegistered(final String document){
        return partnerRepository
                .findByDocument(document.replace(".","").replace("/",""))
                .isEmpty();
    }
}
