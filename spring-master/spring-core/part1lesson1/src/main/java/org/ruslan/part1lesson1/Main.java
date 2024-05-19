package org.ruslan.part1lesson1;

import org.ruslan.part1lesson1.bean.Person;
import org.ruslan.part1lesson1.config.AnnotationConfig;
import org.ruslan.part1lesson1.config.JavaCodeConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
// 4.	Получить бин тремя разными способами (описаны выше).
// Настроить их так, чтобы были созданы два разных объекта.
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");

        Person person = context.getBean(Person.class);
        Person person1 = context.getBean(Person.class);

        System.out.println("xml\n"+ person);
        System.out.println(person1);

//---------------------------------------------------------------------
        AnnotationConfigApplicationContext context1 =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Person person3 = context1.getBean(Person.class);
        Person person4 = context1.getBean(Person.class);

        System.out.println("annotation\n" + person3);
        System.out.println(person4);
//---------------------------------------------------------------------

        AnnotationConfigApplicationContext context2 =
                new AnnotationConfigApplicationContext(JavaCodeConfig.class);
        Person bean = context2.getBean(Person.class);
        Person bean1 = context2.getBean(Person.class);

        System.out.println("java code\n" + bean);
        System.out.println(bean1);

    }
}
