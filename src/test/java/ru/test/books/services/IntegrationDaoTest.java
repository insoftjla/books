package ru.test.books.services;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.test.books.initializers.PostgresInitializer;

@Sql("/data.sql")
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = { PostgresInitializer.Initializer.class })
public abstract class IntegrationDaoTest {

    @BeforeAll
    public static void init() {
        PostgresInitializer.container.start();
    }
}

