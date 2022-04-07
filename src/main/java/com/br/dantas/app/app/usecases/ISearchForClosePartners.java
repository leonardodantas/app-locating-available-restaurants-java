package com.br.dantas.app.app.usecases;

import com.br.dantas.app.domain.PartnerDistance;

import java.util.Collection;

public interface ISearchForClosePartners {

    Collection<PartnerDistance> execute(double latitude, double longitude);
}
