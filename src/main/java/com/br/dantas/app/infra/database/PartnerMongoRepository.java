package com.br.dantas.app.infra.database;

import com.br.dantas.app.app.exceptions.FindPartnerWithAddressAndCoverageAreaException;
import com.br.dantas.app.app.exceptions.SaveEntityException;
import com.br.dantas.app.app.repository.IPartnerRepository;
import com.br.dantas.app.domain.Partner;
import com.br.dantas.app.infra.database.jpa.PartnerSpringDataMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component("mongoRepository")
public class PartnerMongoRepository implements IPartnerRepository {

    private final PartnerSpringDataMongo partnerSpringDataMongo;

    public PartnerMongoRepository(final PartnerSpringDataMongo partnerSpringDataMongo) {
        this.partnerSpringDataMongo = partnerSpringDataMongo;
    }

    @Override
    public Collection<Partner> findAll() {
        return partnerSpringDataMongo.findAll();
    }

    @Override
    public Optional<Partner> findById(final String id) {
        return partnerSpringDataMongo.findById(id);
    }

    @Override
    public Optional<Partner> findByDocument(final String document) {
        return partnerSpringDataMongo.findByDocumentOnlyNumber(document);
    }

    @Override
    public Page<Partner> findAll(final int page, final int size) {
        final var pageable = PageRequest.of(page, size);
        return partnerSpringDataMongo.findAll(pageable);
    }

    @Override
    public Partner save(final Partner partner) {
        try {
            return partnerSpringDataMongo.save(partner);
        } catch (Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Optional<Partner> searchClosePartnersWithCoverageArea(final double latitude, final double longitude) {
        List<Partner> partners;
        try {
            partners = partnerSpringDataMongo.lookForClosePartnersWithCoverageArea(longitude, latitude).stream().toList();
        } catch (Exception e) {
            throw new FindPartnerWithAddressAndCoverageAreaException(e.getMessage());
        }
        return partners.isEmpty() ? Optional.empty() : Optional.of(partners.get(0));
    }

    @Override
    public Collection<Partner> findByLatLong(final double latitude, final double longitude) {
        return partnerSpringDataMongo.findByAddressNear(new Point(longitude, latitude));
    }
}
