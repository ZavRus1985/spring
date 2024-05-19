package org.ruslan.part1lesson1;

import org.ruslan.part1lesson1.bean.Person ;
import org.ruslan.part1lesson1.config.AnnotationConfig ;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMainAnnotation {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Person person = context.getBean(Person.class);
        person.walk();
    }
}
