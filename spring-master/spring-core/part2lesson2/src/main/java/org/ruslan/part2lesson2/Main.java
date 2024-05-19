package org.ruslan.part2lesson2;

import org.ruslan.part2lesson2.bean.Employee;
import org.ruslan.part2lesson2.config.AnnotationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Employee employee = context.getBean(Employee.class);

        System.out.println(employee);
        System.out.println(employee.getJob());
        System.out.println(employee.getPet());
    }
}
