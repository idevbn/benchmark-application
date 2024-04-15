package com.backend.plnapi.beans;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe utilizando padrão Singleton que instancia as
 * configurações de conexão com o banco de dados MongoDB.
 */
@Getter
@Component
@Scope("singleton")
public class MongoDatabaseCredentials {

    private final String database;
    private final String port;
    private final String hostname;
    private final String password;
    private final String username;

    public MongoDatabaseCredentials(@Value("${spring.data.mongodb.database}") final String database,
                                    @Value("${spring.data.mongodb.port}") final String port,
                                    @Value("${spring.data.mongodb.host}")  final String hostname,
                                    @Value("${spring.data.mongodb.password}") final String password,
                                    @Value("${spring.data.mongodb.username}") final String username) {
        this.database = database;
        this.port = port;
        this.hostname = hostname;
        this.password = password;
        this.username = username;
    }

}
