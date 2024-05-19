package org.ruslan.part2lesson1.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class House {

private Door door;
private Window window;
private Material material;

@Autowired
    public House(Door door, Window window, Material material) {
        this.door = door;
        this.window = window;
        this.material = material;
    }
}
