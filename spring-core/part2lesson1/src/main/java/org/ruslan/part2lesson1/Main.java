package org.ruslan.part2lesson1;

import org.ruslan.part2lesson1.bean.Person;
import org.ruslan.part2lesson1.config.AnnotationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println(person);

    }
}
