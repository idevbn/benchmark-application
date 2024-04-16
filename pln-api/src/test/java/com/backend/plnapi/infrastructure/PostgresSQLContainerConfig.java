package com.backend.plnapi.infrastructure;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@Profile("test")
@TestConfiguration
public class PostgresSQLContainerConfig extends PostgreSQLContainer<PostgresSQLContainerConfig> {

    private static final String POSTGRES_IMAGE = "postgres:13.3";
    private static final String DATABASE_NAME = "desafio_planisa";

    private PostgresSQLContainerConfig() {
        super(POSTGRES_IMAGE);
    }

    public static PostgresSQLContainerConfig getInstance() {
        return new PostgresSQLContainerConfig()
                .withDatabaseName(DATABASE_NAME)
                .withInitScript("database/initial_commands.sql");
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
