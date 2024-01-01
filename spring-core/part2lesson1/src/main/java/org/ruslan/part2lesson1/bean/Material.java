package org.ruslan.part2lesson1.bean;

import org.springframework.stereotype.Component;

@Component
public class Material {

    private  final String name;

    public Material() {
        this.name = "tree";
    }
}
