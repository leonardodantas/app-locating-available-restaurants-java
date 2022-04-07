package com.br.dantas.app.infra.controllers.json.request;


import com.br.dantas.app.app.models.IConfig;

import javax.validation.constraints.DecimalMin;

public record ConfigRequestJSON(
        @DecimalMin(value = "1")
        double maxDistance,
        @DecimalMin(value = "1")
        int quantityPartners
) implements IConfig {
}