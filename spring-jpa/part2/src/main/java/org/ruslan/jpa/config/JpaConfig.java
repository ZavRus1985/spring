package org.ruslan.jpa.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@ComponentScan("org.ruslan.jpa")
public class JpaConfig {

    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory(){
        return Persistence.createEntityManagerFactory("my-persistence-unit");
    }
}
