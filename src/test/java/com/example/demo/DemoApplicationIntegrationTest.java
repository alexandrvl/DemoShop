package com.example.demo;

import static com.example.demo.TestTag.INTEGRATION;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Tag(INTEGRATION)
@Profile(INTEGRATION)
public class DemoApplicationIntegrationTest {

    @SuppressWarnings("rawtypes")
    public static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer(DockerImageName.parse("postgres"))
            .withDatabaseName("demo_shop_integration-tests-db")
            .withUsername("test")
            .withPassword("test");
    public static final MockServerContainer MOCK_SERVER_CONTAINER = new MockServerContainer(DockerImageName.parse("jamesdbloom/mockserver"));

    static {
        POSTGRE_SQL_CONTAINER.start();
        MOCK_SERVER_CONTAINER.start();
    }

    @DynamicPropertySource
    static void applyProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
    }

    @Test
    void contextLoads() {
    }

}
