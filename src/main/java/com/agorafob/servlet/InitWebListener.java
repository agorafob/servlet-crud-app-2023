package com.agorafob.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.agorafob.dbconnect.ConnectionBuilderFactory;
import com.agorafob.dbconnect.DbConnectionBuilder;
import com.agorafob.dbconnect.PoolDbConnectionBuilder;
import com.agorafob.util.AppConfig;

import java.io.IOException;
import java.net.URISyntaxException;

@WebListener
public class InitWebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener was created!");
        try {
            AppConfig.initAppConfig();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener was destroyed!");
        DbConnectionBuilder dbConnectionBuilder = ConnectionBuilderFactory.getInstance().getDbConnectionBuilder();
        if (dbConnectionBuilder instanceof PoolDbConnectionBuilder) {
            ((PoolDbConnectionBuilder) dbConnectionBuilder).closeDataSource();
        }
    }
}
