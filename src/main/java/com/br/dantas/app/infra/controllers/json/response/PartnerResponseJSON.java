package com.br.dantas.app.infra.controllers.json.response;


import com.br.dantas.app.domain.Partner;

public record PartnerResponseJSON(String id, String tradingName, String ownerName,
                                  String document) {

    public static PartnerResponseJSON from(Partner partner) {
        return new PartnerResponseJSON(partner.getId(), partner.getTradingName(), partner.getOwnerName(), partner.getDocument());
    }
}
