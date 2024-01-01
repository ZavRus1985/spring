package org.ruslan.part2lesson3.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
@PropertySource("classpath:application.properties")
public class JdbcConnection {

    @Value("${jdbc.url}")
    private  String url;
    @Value("${jdbc.ursername}")
    private  String username;
    @Value("${jdbc.password}")
    private String password;


    public Connection connection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
