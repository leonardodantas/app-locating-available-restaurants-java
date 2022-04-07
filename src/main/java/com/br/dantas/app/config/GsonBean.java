package com.br.dantas.app.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonBean {

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }
}
