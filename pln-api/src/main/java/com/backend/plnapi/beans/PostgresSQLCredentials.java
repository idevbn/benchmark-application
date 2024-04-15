package com.backend.plnapi.beans;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe utilizando padrão Singleton que instancia as
 * configurações de conexão com o banco de dados.
 */
@Getter
@Component
@Scope("singleton")
public class PostgresSQLCredentials {

    private final String database;
    private final String username;
    private final String password;


    public PostgresSQLCredentials(@Value("${spring.datasource.url}") final String database,
                                  @Value("${spring.datasource.username}") final String username,
                                  @Value("${spring.datasource.password}") final String password) {
        this.database = database;
        this.username = username;
        this.password = password;
    }

}
