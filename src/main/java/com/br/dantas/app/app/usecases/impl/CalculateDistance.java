package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.domain.Partner;

import java.math.BigDecimal;

import static java.lang.Math.*;

public class CalculateDistance {

    private final static double EARTH_RADIUS = 6371.01;

    public static BigDecimal betweenLatLong(final double latitude,final double longitude,final Partner partner) {
        final var firstLatToRad = toRadians(latitude);
        final var secondLatToRad = toRadians(partner.getLatitude());

        final var distance = getDistance(longitude, partner, firstLatToRad, secondLatToRad);

        return BigDecimal.valueOf(distance);
    }

    private static double getDistance(double longitude, Partner partner, double firstLatToRad, double secondLatToRad) {
        final var deltaLongitudeInRad = toRadians(partner.getLongitude()
                - longitude);

        return calculateDistance(firstLatToRad, secondLatToRad, deltaLongitudeInRad);
    }

    private static double calculateDistance(final double firstLatToRad,final double secondLatToRad,final double deltaLongitudeInRad) {
        return acos(cos(firstLatToRad) * cos(secondLatToRad)
                * cos(deltaLongitudeInRad) + sin(firstLatToRad)
                * sin(secondLatToRad))
                * EARTH_RADIUS;
    }
}
