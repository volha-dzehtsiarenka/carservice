package com.degtyarenko;

import org.testcontainers.containers.PostgreSQLContainer;

public class BaseTest {

    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:14.1");
}
