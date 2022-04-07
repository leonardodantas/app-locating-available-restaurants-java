package com.br.dantas.app.infra.database.jpa;

import com.br.dantas.app.domain.Partner;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface PartnerSpringDataMongo extends MongoRepository<Partner, String> {
    Optional<Partner> findByDocument(final String document);
    Collection<Partner> findByAddressNear(final Point point);
    @Query("{ $and: [ { address: { $near : { $geometry: { type: 'Point',  coordinates: [ ?0, ?1 ] } } } }, { coverageArea: { $geoIntersects: { $geometry: { type: 'Point' , coordinates: [ ?0, ?1 ] } } } } ] }")
    Collection<Partner> lookForClosePartnersWithCoverageArea(final double longitude,final double latitude);

}
