package org.ruslan.part1lesson3.bean;

import org.springframework.stereotype.Component;

@Component
public class Bird implements Flyable{

    @Override
    public void fly() {
        System.out.println("the bird flew");
    }
}
