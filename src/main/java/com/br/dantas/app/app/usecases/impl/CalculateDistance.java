package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.domain.Partner;

import java.math.BigDecimal;

public class CalculateDistance {

    private final static double EARTH_RADIUS = 6371.01;

    public static BigDecimal betweenLatLong(final double latitude,final double longitude,final Partner partner) {
        final var firstLatToRad = Math.toRadians(latitude);
        final var secondLatToRad = Math.toRadians(partner.getLatitude());

        final var deltaLongitudeInRad = Math.toRadians(partner.getLongitude()
                - longitude);

        final var distance = calculateDistance(firstLatToRad, secondLatToRad, deltaLongitudeInRad);

        return BigDecimal.valueOf(distance);
    }

    private static double calculateDistance(final double firstLatToRad,final double secondLatToRad,final double deltaLongitudeInRad) {
        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
                * Math.sin(secondLatToRad))
                * EARTH_RADIUS;
    }
}
