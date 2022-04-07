package com.br.dantas.app.app.repository;

import com.br.dantas.app.domain.Config;

import java.util.Optional;

public interface IConfigRepository {
    Optional<Config> findConfig();
    void save(Config config);
}
