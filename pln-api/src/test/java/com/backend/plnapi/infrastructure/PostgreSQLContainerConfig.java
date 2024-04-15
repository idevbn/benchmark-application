package com.backend.plnapi.infrastructure;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@Profile("test")
@TestConfiguration
public class PostgreSQLContainerConfig extends PostgreSQLContainer<PostgreSQLContainerConfig> {

    private static final String POSTGRES_IMAGE = "postgres:13.3";

    private PostgreSQLContainerConfig() {
        super(POSTGRES_IMAGE);
    }

    public static PostgreSQLContainerConfig getInstance() {
        return new PostgreSQLContainerConfig()
                .withDatabaseName("desafio_planisa");
    }

    @Override
    public void start() {
        super.start();

        System.setProperty("DB_URL", this.getJdbcUrl());
        System.setProperty("DB_USERNAME", this.getUsername());
        System.setProperty("DB_PASSWORD", this.getPassword());
    }

    @Override
    public void stop() {}

}
