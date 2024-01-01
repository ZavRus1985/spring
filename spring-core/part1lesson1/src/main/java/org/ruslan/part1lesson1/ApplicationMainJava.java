package org.ruslan.part1lesson1;

import org.ruslan.part1lesson1.bean.Person;
import org.ruslan.part1lesson1.config.JavaCodeConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMainJava {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JavaCodeConfig.class);
        Person bean = context.getBean(Person.class);
        bean.walk();
    }
}
