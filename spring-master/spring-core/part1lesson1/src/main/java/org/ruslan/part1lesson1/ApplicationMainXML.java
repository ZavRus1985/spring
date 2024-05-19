package org.ruslan.part1lesson1;


import org.ruslan.part1lesson1.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationMainXML {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Person person = context.getBean("person", Person.class); // getBean(Person.class);
        person.walk();
    }
}
