package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.exceptions.ErrorsException;
import com.br.dantas.app.app.exceptions.PartnerNotFound;
import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.ISearchForPartnerThatOperatesInTheCurrentArea;
import com.br.dantas.app.domain.Partner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchForPartnerThatOperatesInTheCurrentArea implements ISearchForPartnerThatOperatesInTheCurrentArea {

    private final IPartnerRepository partnerRepository;

    public SearchForPartnerThatOperatesInTheCurrentArea(@Qualifier("mongoRepository")final IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Partner execute(final double latitude, final double longitude) {
        return this.partnerRepository
                .searchClosePartnersWithCoverageArea(latitude, longitude)
                .orElseThrow(() -> new PartnerNotFound(ErrorsException.NO_PARTNERS_FOUND_FOR_THE_CURRENT_AREA.getMessage()));
    }
    
}
