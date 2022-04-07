package com.br.dantas.app.infra.controllers.json.request;


import com.br.dantas.app.app.models.ICoverageArea;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public record CoverageAreaRequestJSON(
        @NotNull
        Collection<Collection<Collection<Collection<Double>>>> coordinates) implements ICoverageArea {

}
