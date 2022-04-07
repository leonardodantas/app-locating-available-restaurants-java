package com.br.dantas.app.app.usecases;

import com.br.dantas.app.app.models.IPartner;
import com.br.dantas.app.domain.Partner;

public interface IAddPartner {
    Partner execute(IPartner partner);
}
