package org.ruslan.part2lesson1.bean;

import org.springframework.stereotype.Component;

@Component
public class Door {

private final String type;

    public Door() {
        this.type = "interior door";
    }
}
