package org.ruslan.part1lesson2.bean;

import org.springframework.stereotype.Component;

@Component
public class Employee {

    private final String name;
 // private final String name = "Bob"; //2-ой вариант;
    private final int age;
//  private final int age = 20;  //2-ой вариант;

    public Employee() {
        name = "Bob"; //1-ый вариант
        age = 20; //1-ый вариант
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
