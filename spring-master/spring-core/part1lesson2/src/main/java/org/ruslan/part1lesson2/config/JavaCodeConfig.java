package org.ruslan.part1lesson2.config;

import org.ruslan.part1lesson2.bean.Employee;
import org.ruslan.part1lesson2.bean.User;
import org.springframework.context.annotation.Bean;

public class JavaCodeConfig {
    @Bean
    public Employee employee() {
        return new Employee();
    }

    @Bean
    public User User() {
        return new User();
    }
}
