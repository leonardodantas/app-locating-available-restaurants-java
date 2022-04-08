package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.app.usecases.IGetConfig;
import com.br.dantas.app.app.usecases.ISearchForClosePartners;
import com.br.dantas.app.domain.PartnerDistance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class SearchForClosePartners implements ISearchForClosePartners {

    private final IPartnerRepository partnerRepository;
    private final IGetConfig getConfig;

    public SearchForClosePartners(@Qualifier("mongoRepository") final IPartnerRepository partnerRepository, final IGetConfig getConfig) {
        this.partnerRepository = partnerRepository;
        this.getConfig = getConfig;
    }

    @Override
    public Collection<PartnerDistance> execute(final double latitude, final double longitude) {
        final var config = this.getConfig.execute();

        return partnerRepository.findByLatLong(latitude, longitude)
                .stream().map(partner -> PartnerDistance.of(partner, latitude, longitude)).toList()
                .stream().filter(partner -> partner.getDistance().compareTo(BigDecimal.valueOf(config.getMaxDistance())) < 1).toList()
                .stream().limit(config.getQuantityPartners()).toList();
    }
}
