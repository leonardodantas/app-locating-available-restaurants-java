package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.repository.IConfigRepository;
import com.br.dantas.app.app.usecases.IGetConfig;
import com.br.dantas.app.domain.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetConfig implements IGetConfig {

    private final IConfigRepository configRepository;
    private final double maxDistance;
    private final int quantityPartners;

    public GetConfig(final IConfigRepository configRepository, @Value("${list.partnes.max.distance}")final double maxDistance, @Value("${list.partnes.quantity.partners}")final int quantityPartners) {
        this.configRepository = configRepository;
        this.maxDistance = maxDistance;
        this.quantityPartners = quantityPartners;
    }

    @Override
    public Config execute() {
        return configRepository
                .findConfig()
                .orElseGet(this::getConfigBasic);
    }

    private Config getConfigBasic(){
        return Config.of(this.maxDistance, this.quantityPartners);
    }
}
