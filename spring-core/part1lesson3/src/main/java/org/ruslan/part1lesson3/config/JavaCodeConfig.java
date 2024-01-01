package org.ruslan.part1lesson3.config;

import org.ruslan.part1lesson3.bean.Bird;
import org.ruslan.part1lesson3.bean.Helicopter;
import org.ruslan.part1lesson3.bean.Plane;
import org.springframework.context.annotation.Bean;

public class JavaCodeConfig {

    @Bean
    public Bird bird() {
        return new Bird();
    }

    @Bean
    public Helicopter helicopter() {
        return new Helicopter();
    }

    @Bean
    public Plane plane() {
        return new Plane();
    }

}
