package com.br.dantas.app.infra.controllers.json.response;

import com.br.dantas.app.domain.Config;

import java.time.LocalDateTime;

public record ConfigResponseJSON(double maxDistance,
                                 int quantityPartners,
                                 LocalDateTime date) {
    public static ConfigResponseJSON from(Config config) {
        return new ConfigResponseJSON(config.getMaxDistance(), config.getQuantityPartners(), config.getDate());
    }
}
