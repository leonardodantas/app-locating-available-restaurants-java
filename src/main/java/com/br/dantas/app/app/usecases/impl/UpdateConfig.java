package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.converter.ConfigConverter;
import com.br.dantas.app.app.models.IConfig;
import com.br.dantas.app.app.repository.IConfigRepository;
import com.br.dantas.app.app.usecases.IUpdateConfig;
import com.br.dantas.app.domain.Config;
import org.springframework.stereotype.Service;

@Service
public class UpdateConfig implements IUpdateConfig {

    private final IConfigRepository configRepository;

    public UpdateConfig(final IConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public void execute(final IConfig config) {
        Config configDomain = ConfigConverter.toDomain(config);
        configRepository.save(configDomain);
    }
}
