package com.br.dantas.app.domain;

import com.br.dantas.app.app.usecases.CalculateDistance;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class PartnerDistance implements Comparable<PartnerDistance>{

    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private BigDecimal distance;

    private PartnerDistance(final Partner partner, final BigDecimal distance) {
        this.id = partner.getId();
        this.tradingName = partner.getTradingName();
        this.ownerName = partner.getOwnerName();
        this.document = partner.getDocument();
        this.distance = distance;
    }

    public static PartnerDistance of(final Partner partner, final double latitude, final double longitude) {
        final var distance = CalculateDistance.betweenLatLong(latitude, longitude, partner);
        return new PartnerDistance(partner, distance);
    }

    @Override
    public int compareTo(final PartnerDistance otherPartnerDistance) {
        return this.distance.compareTo(otherPartnerDistance.getDistance());
    }

    public BigDecimal getDistance() {
        return distance.setScale(2, RoundingMode.HALF_DOWN);
    }
}
