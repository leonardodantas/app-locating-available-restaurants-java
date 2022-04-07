package com.br.dantas.app.infra.database;

import com.br.dantas.app.app.exceptions.SaveEntityException;
import com.br.dantas.app.app.repository.IConfigRepository;
import com.br.dantas.app.domain.Config;
import com.br.dantas.app.infra.database.jpa.ConfigSpringDataMongo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConfigRepository implements IConfigRepository {

    private final ConfigSpringDataMongo configSpringDataMongo;

    public ConfigRepository(final ConfigSpringDataMongo configSpringDataMongo) {
        this.configSpringDataMongo = configSpringDataMongo;
    }

    @Override
    public Optional<Config> findConfig() {
        final var configs = configSpringDataMongo.findAllByOrderByDateDesc().stream().toList();
        return configs.isEmpty()? Optional.empty() : Optional.of(configs.get(0));
    }

    @Override
    public void save(final Config config) {
        try {
            configSpringDataMongo.save(config);
        } catch (Exception e){
            throw new SaveEntityException(e.getMessage());
        }
    }
}
