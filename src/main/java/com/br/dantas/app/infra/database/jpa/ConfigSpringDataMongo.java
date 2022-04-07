package com.br.dantas.app.infra.database.jpa;

import com.br.dantas.app.domain.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ConfigSpringDataMongo extends MongoRepository<Config, String> {
    Collection<Config> findAllByOrderByDateDesc();
}
