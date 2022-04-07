package com.br.dantas.app.app.converter;

import com.br.dantas.app.app.models.IAddress;
import com.br.dantas.app.app.models.ICoverageArea;
import com.br.dantas.app.app.models.IPartner;
import com.br.dantas.app.domain.Address;
import com.br.dantas.app.domain.CoverageArea;
import com.br.dantas.app.domain.Partner;

import java.util.UUID;

public class PartnerConverter {

    public static Partner toDomain(final IPartner partner) {

        return Partner.builder()
                .id(UUID.randomUUID().toString())
                .document(partner.document())
                .ownerName(partner.ownerName())
                .tradingName(partner.tradingName())
                .address(toDomain(partner.address()))
                .coverageArea(toDomain(partner.coverageArea()))
                .build();
    }

    private static CoverageArea toDomain(final ICoverageArea coverageArea) {
        return CoverageArea.builder()
                .type("MultiPolygon")
                .coordinates(coverageArea.coordinates())
                .build();
    }

    private static Address toDomain(final IAddress address) {
        return Address.builder()
                .type("Point")
                .coordinates(address.coordinates())
                .build();
    }
}
