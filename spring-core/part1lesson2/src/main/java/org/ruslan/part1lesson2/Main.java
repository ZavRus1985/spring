package org.ruslan.part1lesson2;

import org.ruslan.part1lesson2.bean.Employee;
import org.ruslan.part1lesson2.bean.User;
import org.ruslan.part1lesson2.config.AnnotationConfig;
import org.ruslan.part1lesson2.config.JavaCodeConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Employee employee = context.getBean(Employee.class);
        System.out.println(employee);

        //---------------------------------------------------------------------

        ApplicationContext context1 = new AnnotationConfigApplicationContext(JavaCodeConfig.class);
        Employee employee1 = context1.getBean(Employee.class);
        System.out.println(employee1);

        //---------------------------------------------------------------------

        User user = context1.getBean(User.class);
        System.out.println(user);

    }
}
