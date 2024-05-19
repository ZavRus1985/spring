package org.ruslan.part1lesson1.config;

import org.ruslan.part1lesson1.bean.Person ;
import org.springframework.context.annotation.Bean;

public class JavaCodeConfig {

    @Bean
//    @Scope("prototype")
    public Person person() {
        return new Person();
    }
}
