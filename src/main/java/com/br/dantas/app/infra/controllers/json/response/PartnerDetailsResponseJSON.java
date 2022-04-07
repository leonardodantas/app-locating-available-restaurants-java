package com.br.dantas.app.infra.controllers.json.response;


import com.br.dantas.app.domain.Partner;

public record PartnerDetailsResponseJSON(String id, String tradingName, String ownerName,
                                         String document,
                                         CoverageAreaResponseJSON coverageArea,
                                         AddressResponseJSON address) {

    public static PartnerDetailsResponseJSON from(Partner partner) {
        return new PartnerDetailsResponseJSON(partner.getId(), partner.getTradingName(), partner.getOwnerName(), partner.getDocument(), CoverageAreaResponseJSON.from(partner.getCoverageArea()), AddressResponseJSON.from(partner.getAddress()));
    }
}
