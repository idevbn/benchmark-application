package com.backend.plnapi.beans;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe utilizando padrão Singleton que instancia as
 * configurações de conexão com a API externa.
 */
@Getter
@Component
@Scope("singleton")
public class ApiCredentials {

    private final String apiCovidRequestUrl;

    private final String apiKey;

    public ApiCredentials(@Value("${api.covid.url}") final String apiCovidRequestUrl,
                          @Value("${api.covid.key}") final String apiKey) {
        this.apiCovidRequestUrl = apiCovidRequestUrl;
        this.apiKey = apiKey;
    }

}
