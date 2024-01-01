package org.ruslan.part1lesson2.bean;

import javax.annotation.PostConstruct;

public class User {
    private String name;
    private int age;

    public User() {
        System.out.println("Constructor User");
    }

//-----------------------------------------------------------------------------
//5.Решить проблему назначения полей третьим способом
// (1-ый и 2-ой способы должны быть представлены в пунктах 3 и 4,
// в 5 пункте должен быть представлен 3-ий способ решения данной проблемы).

    @PostConstruct  //3-ий вариант
    private void postConstruct() {
        this.age = 100;
        this.name = "Bob";
    }
//-----------------------------------------------------------------------------


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
