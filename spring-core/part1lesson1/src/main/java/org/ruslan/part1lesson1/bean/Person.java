package org.ruslan.part1lesson1.bean;

import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Person {
    public void walk() {
        System.out.println("walking");
    }
}
