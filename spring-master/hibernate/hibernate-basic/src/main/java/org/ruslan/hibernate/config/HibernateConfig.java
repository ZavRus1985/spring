package org.ruslan.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ruslan.hibernate.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfig {

//    @Bean
//    public SessionFactory sessionFactory() {
//        return new Configuration()
//                .configure("hibernate-config.xml")
//                .addAnnotatedClass(Car.class)
//                .buildSessionFactory();
//    }
}
