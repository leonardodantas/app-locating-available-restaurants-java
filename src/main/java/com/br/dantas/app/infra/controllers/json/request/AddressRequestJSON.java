package com.br.dantas.app.infra.controllers.json.request;


import com.br.dantas.app.app.models.IAddress;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public record AddressRequestJSON(
        @NotNull
        ArrayList<Double> coordinates) implements IAddress {

}
