package org.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class ConnManager {

    private static final String PATH_CONFIG_FILE = "hibernate.cfg.xml";

    private static final String DEVELOPMENT_ENVIRONMENT;
    private static final String PASSWORD;
    private static final String USERNAME;
    private static final String HIBERNATE_DIALECT;
    private static final String HIBERNATE_CONNECTION_URL;
    private static final String HIBERNATE_CONNECTION_DRIVER_CLASS;
    static {
        DEVELOPMENT_ENVIRONMENT = System.getenv("DEVELOPMENT_ENVIRONMENT");

        if (DEVELOPMENT_ENVIRONMENT.equals("PRODUCTION")) {
            USERNAME = System.getenv("PG_USERNAME");
            PASSWORD = System.getenv("PG_PASSWORD");
            HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
            HIBERNATE_CONNECTION_URL = "jdbc:postgresql://localhost:5432/hibernate";
            HIBERNATE_CONNECTION_DRIVER_CLASS = "org.postgresql.Driver";
        } else {
            USERNAME = System.getenv("H2_USERNAME");
            PASSWORD = System.getenv("H2_PASSWORD");
            HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect";
            HIBERNATE_CONNECTION_URL = "jdbc:h2:mem:testdb";
            HIBERNATE_CONNECTION_DRIVER_CLASS = "org.h2.Driver";
        }
    }
    public static SessionFactory getConnection() {
        SessionFactory sessionFactory = new Configuration()
                .configure(PATH_CONFIG_FILE)
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.connection.url", HIBERNATE_CONNECTION_URL)
                .setProperty("hibernate.dialect", HIBERNATE_DIALECT)
                .setProperty("hibernate.connection.driver_class", HIBERNATE_CONNECTION_DRIVER_CLASS)
                .buildSessionFactory();
        return sessionFactory;
    }
}
