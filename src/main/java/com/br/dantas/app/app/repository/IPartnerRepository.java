package com.br.dantas.app.app.repository;


import com.br.dantas.app.domain.Partner;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface IPartnerRepository {
    Collection<Partner> findAll();
    Optional<Partner> findById(String id);
    Optional<Partner> findByDocument(String document);
    Page<Partner> findAll(int page, int size);
    Partner save(Partner partner);
    Optional<Partner> searchClosePartnersWithCoverageArea(double latitude, double longitude);
    Collection<Partner> findByLatLong(double latitude, double longitude);
}
