package org.ruslan.part1lesson3.bean;

import org.springframework.stereotype.Component;

@Component
public class Plane implements Flyable{
    @Override
    public void fly() {
        System.out.println("the plane flew");
    }
}
