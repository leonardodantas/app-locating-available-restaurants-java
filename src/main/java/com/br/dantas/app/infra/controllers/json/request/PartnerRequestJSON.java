package com.br.dantas.app.infra.controllers.json.request;


import com.br.dantas.app.app.models.IPartner;
import com.br.dantas.app.infra.annotations.DocumentValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PartnerRequestJSON(

        @NotBlank
        String tradingName,
        @NotBlank
        String ownerName,
        @DocumentValid
        String document,
        @NotNull
        CoverageAreaRequestJSON coverageArea,
        @NotNull
        AddressRequestJSON address) implements IPartner {

}
