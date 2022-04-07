package com.br.dantas.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@Configuration
@EnableSwagger2
public class Swagger implements WebMvcConfigurer {

    @Value("${spring.application.name}")
    private String title;

    @Value("${spring.application.description}")
    private String description;

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.br.dantas.app.infra.controllers"))
                .build()
                .tags(
                        new Tag("Create Partner", "", 1),
                        new Tag("Get all Partners", "", 2),
                        new Tag("Find partner according to parameter", "", 3),
                        new Tag("Find closest partners", "", 4),
                        new Tag("Search for a partner who works in the current area", "", 5),
                        new Tag("Generate data", "", 6),
                        new Tag("Configuration", "", 7))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(title.toUpperCase(Locale.ROOT))
                .description(description)
                .version(version)
                .contact(contact())
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private Contact contact() {
        return new Contact("Leonardo Rodrigues Dantas", "https://www.linkedin.com/in/leonardo-rodrigues-dantas/", "leonardordnt1317@gmail.com");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}