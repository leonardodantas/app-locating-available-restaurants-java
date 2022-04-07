package com.br.dantas.app.infra.controllers.json.response;


import com.br.dantas.app.domain.CoverageArea;

import java.util.Collection;

public record CoverageAreaResponseJSON(String type,
                                       Collection<Collection<Collection<Collection<Double>>>> coordinates) {

    public static CoverageAreaResponseJSON from(CoverageArea coverageArea) {
        return new CoverageAreaResponseJSON(coverageArea.getType(), coverageArea.getCoordinates());
    }
}
