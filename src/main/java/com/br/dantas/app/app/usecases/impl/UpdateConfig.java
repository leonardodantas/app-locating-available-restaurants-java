package com.br.dantas.app.app.usecases.impl;

import com.br.dantas.app.app.converter.ConfigConverter;
import com.br.dantas.app.app.models.IConfig;
import com.br.dantas.app.app.repository.IConfigRepository;
import com.br.dantas.app.app.usecases.IUpdateConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class UpdateConfig implements IUpdateConfig {

    private final IConfigRepository configRepository;

    public UpdateConfig(final IConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    @CacheEvict(value = "config", allEntries = true)
    public void execute(final IConfig config) {
        final var configDomain = ConfigConverter.toDomain(config);
        configRepository.save(configDomain);
    }
}
