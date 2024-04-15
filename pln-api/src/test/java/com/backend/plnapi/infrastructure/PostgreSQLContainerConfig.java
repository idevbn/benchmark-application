package com.backend.plnapi.infrastructure;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.MongoDBContainer;

@Profile("test")
@TestConfiguration
public class MongoContainerConfig extends MongoDBContainer {

    private static final String MONGO_IMAGE = "mongo:latest";

    private MongoContainerConfig() {
        super(MONGO_IMAGE);
    }

    public static MongoContainerConfig getInstance() {
        return (MongoContainerConfig) new MongoContainerConfig()
                .withReuse(true);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {}

}
