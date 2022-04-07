package com.br.dantas.app.app.usecases;

import com.br.dantas.app.domain.Partner;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface IFIndAllPartner {

    Collection<Partner> execute();
    Page<Partner> execute(int page, int size);
}
