package org.ruslan.part2lesson1.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private House house;
    private Pet pet;
@Autowired
    public Person(House house, Pet pet) {
        this.house = house;
        this.pet = pet;
    }
}
