package com.br.dantas.app.infra.controllers.json.response;

import com.br.dantas.app.domain.PartnerDistance;

import java.math.BigDecimal;

public record PartnerWithDistanceResponseJSON(String id, String tradingName, String ownerName,
                                              String document, BigDecimal distance) {

    public static PartnerWithDistanceResponseJSON from(final PartnerDistance partner) {
        return new PartnerWithDistanceResponseJSON(partner.getId(), partner.getTradingName(), partner.getOwnerName(), partner.getDocument(), partner.getDistance());
    }
}
