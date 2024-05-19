package org.ruslan.part1lesson3.bean;

import org.springframework.stereotype.Component;

@Component
public class Helicopter implements Flyable{
    @Override
    public void fly() {
        System.out.println("the Helicopter flew");
    }
}
