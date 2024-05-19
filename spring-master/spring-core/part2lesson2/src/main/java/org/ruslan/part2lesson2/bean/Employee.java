package org.ruslan.part2lesson2.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Employee {
   @Autowired
   @Qualifier("engineer")
    private Job job;
    private Pet pet;

    @Autowired(required = false)
    public void setPet(Pet pet) {
        this.pet = pet;
    }

//@Autowired
//    public Employee(@Qualifier("engineer") Job job, Pet pet) { //public Employee(@Qualifier("engineer") Job job, Pet pet) {
//        this.job = job;
//        this.pet = pet;
//    }

    public Job getJob() {
        return job;
    }

    public Pet getPet() {
        return pet;
    }
}
