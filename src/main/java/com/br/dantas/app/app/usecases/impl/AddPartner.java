package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.converter.PartnerConverter;
import com.br.dantas.app.app.models.IPartner;
import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.IAddPartner;
import com.br.dantas.app.domain.Partner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AddPartner implements IAddPartner {

    private final IPartnerRepository partnerRepository;

    public AddPartner(@Qualifier("mongoRepository") final IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Partner execute(final IPartner partner) {
        final var partnerDomain = PartnerConverter.toDomain(partner);
        return partnerRepository.save(partnerDomain);
    }
}
