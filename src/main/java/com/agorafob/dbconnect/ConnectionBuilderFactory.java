package com.agorafob.dbconnect;

public class ConnectionBuilderFactory {

    private static ConnectionBuilderFactory instance;
    private final DbConnectionBuilder dbConnectionBuilder = new PoolDbConnectionBuilder();

    private ConnectionBuilderFactory() {
    }

    public static synchronized ConnectionBuilderFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionBuilderFactory();
        }
        return instance;
    }

    public DbConnectionBuilder getDbConnectionBuilder() {
        return dbConnectionBuilder;
    }
}
