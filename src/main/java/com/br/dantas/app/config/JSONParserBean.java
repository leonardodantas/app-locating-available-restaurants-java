package com.br.dantas.app.config;

import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONParserBean {

    @Bean
    public JSONParser parser() {
        return new JSONParser();
    }
}
