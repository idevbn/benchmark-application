package com.backend.plnapi.infrastructure;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MongoDBContainer;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoIntegrationTestInitializer {

    @ClassRule
    public static MongoDBContainer mongoDBContainer = MongoContainerConfig.getInstance();

    @Test
    public void simpleAssertIntegrationTestInitializer() {
        Assert.assertTrue(true);
    }

}
