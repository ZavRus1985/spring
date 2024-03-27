package org.ruslan.hib.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.ruslan.hib.entity.manytomany.Order;
import org.ruslan.hib.entity.manytomany.Product;
import org.ruslan.hib.entity.onetomany.Category;
import org.ruslan.hib.entity.onetomany.Producer;
import org.ruslan.hib.entity.onetoone.AccountCustomer;
import org.ruslan.hib.entity.onetoone.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

@ComponentScan("org.ruslan.hib")
public class AppConfig {

    @Bean
    public SessionFactory sessionFactory(Configuration configuration){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Primary
    @Bean
    public Configuration configuration(){
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/lessonhibernate");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "ruslan");
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.GLOBALLY_QUOTED_IDENTIFIERS, "true");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(AccountCustomer.class);
        configuration.addAnnotatedClass(Producer.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Order.class);


        return configuration;
    }
}
