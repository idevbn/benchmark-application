package com.backend.plnapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * Bean que retorna uma instância de {@link RestTemplate}
     * com configurações default.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
