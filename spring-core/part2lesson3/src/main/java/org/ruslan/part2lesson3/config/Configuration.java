package org.ruslan.part2lesson3.config;

import org.ruslan.part2lesson3.bean.JdbcConnection;
import org.ruslan.part2lesson3.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
@ComponentScan("org.ruslan.part2lesson3")

public class Configuration {
    @Autowired
    JdbcConnection jdbcConnection;
    @Bean
    private Connection connection() throws SQLException {

        return jdbcConnection.connection();
    }

    //@Bean
//    public CarRepository carRepository() throws SQLException {
//        return new CarRepository(connection());
//    }

}
