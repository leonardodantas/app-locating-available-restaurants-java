package com.br.dantas.app.infra.controllers.json.response;


import com.br.dantas.app.domain.Address;

import java.util.Collection;

public record AddressResponseJSON(String type, Collection<Double> coordinates) {

    public static AddressResponseJSON from(Address address) {
        return new AddressResponseJSON(address.getType(), address.getCoordinates());
    }
}
