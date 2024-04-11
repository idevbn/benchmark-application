package com.backend.plnapi.configs;

import com.backend.plnapi.beans.MongoDatabaseCredentials;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoDatabaseCredentials mongoDatabaseCredentials;

    public MongoConfig(final MongoDatabaseCredentials mongoDatabaseCredentials) {
        this.mongoDatabaseCredentials = mongoDatabaseCredentials;
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString(this.getUri());

        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return this.mongoDatabaseCredentials.getDatabase();
    }

    private String getUri() {
        final String pattern = "mongodb://%s:%S@%s:%s/";

        return String.format(
                pattern,
                this.mongoDatabaseCredentials.getUsername(),
                this.mongoDatabaseCredentials.getPassword(),
                this.mongoDatabaseCredentials.getHostname(),
                this.mongoDatabaseCredentials.getPort(),
                this.getDatabaseName()
        );
    }

}
