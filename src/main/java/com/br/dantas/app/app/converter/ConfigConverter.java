package com.br.dantas.app.app.converter;

import com.br.dantas.app.app.models.IConfig;
import com.br.dantas.app.domain.Config;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConfigConverter {

    public static Config toDomain(final IConfig config){
        return Config.builder()
                .id(UUID.randomUUID().toString())
                .maxDistance(config.maxDistance())
                .quantityPartners(config.quantityPartners())
                .date(LocalDateTime.now())
                .build();
    }
}
