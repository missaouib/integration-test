package com.anderscore.testcontainers.util;

import org.springframework.stereotype.Component;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

import static java.util.Arrays.asList;

@Component
public class DatabaseContainerHolder {

    private static final int CONTAINER_DB_PORT = 3306;
    private static final String DB_SCHEMA = "scheduler";
    private static final String DB_USER = "mysql";
    private static final String DB_PASSWD = "docker";

    private int hostDbPort;
    private JdbcDatabaseContainer<?> instance;

    public DatabaseContainerHolder(){
        hostDbPort = 0;
        refresh();
    }
    private JdbcDatabaseContainer<?> newContainer(){
        JdbcDatabaseContainer<?> container = (MySQLContainer)new MySQLContainer("mysql:8.0.18")
                .withDatabaseName(DB_SCHEMA)
                .withUsername(DB_USER)
                .withPassword(DB_PASSWD)
                .withCommand("mysqld",
                        "--lower_case_table_names=1",
                        "--log_bin_trust_function_creators=1",
                        "--default-authentication-plugin=mysql_native_password");



        if (hostDbPort > 0){
            container.setPortBindings(asList(hostDbPort + ":" + CONTAINER_DB_PORT));
        }
        container.start();
        return container;
    }

    public JdbcDatabaseContainer<?> get(){
        return instance;
    }

    //tag::refresh[]
    public void refresh(){
        if (instance != null && instance.isRunning()){
            instance.stop();
        }

        instance = newContainer();
        hostDbPort = instance.getMappedPort(CONTAINER_DB_PORT);
    }
    //end::refresh[]
}