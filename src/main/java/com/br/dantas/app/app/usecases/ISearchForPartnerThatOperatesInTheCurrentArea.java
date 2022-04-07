package com.br.dantas.app.app.usecases;

import com.br.dantas.app.domain.Partner;

public interface ISearchForPartnerThatOperatesInTheCurrentArea {
    Partner execute(double latitude, double longitude);
}
