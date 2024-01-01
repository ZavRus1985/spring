package org.ruslan.part1lesson3;

import org.ruslan.part1lesson3.bean.Bird;
import org.ruslan.part1lesson3.bean.Flyable;
import org.ruslan.part1lesson3.bean.Helicopter;
import org.ruslan.part1lesson3.bean.Plane;
import org.ruslan.part1lesson3.config.AnnotationConfig;
import org.ruslan.part1lesson3.config.JavaCodeConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);
        System.out.println("----------------------------------------\n" + "Annotation config");
        Bird bird = context.getBean(Bird.class);
        bird.fly();

        Helicopter helicopter = context.getBean(Helicopter.class);
        helicopter.fly();

        Plane plane = context.getBean(Plane.class);
        plane.fly();
        System.out.println("----------------------------------------");

//-------------------------------------------------

        ApplicationContext context1 =
                new AnnotationConfigApplicationContext(JavaCodeConfig.class);

        System.out.println("JavaCode config");
        Bird bird1 = context1.getBean(Bird.class);
        bird1.fly();

        Helicopter helicopter1 = context1.getBean(Helicopter.class);
        helicopter1.fly();

        Plane plane1 = context1.getBean(Plane.class);
        plane1.fly();
        System.out.println("----------------------------------------");

        //-------------------------------------------------

/*
3.	Попробовать получить бин по интерфейсу (Flyable.class). Понять, в чем проблема.
4.	Исправить код так, чтобы проблема исчезла (что нужно поменять в проекте,
    чтобы бин был внедрен по интерфейсу).
    Не прибегать к сторонним аннотациям (предлагаемых, например, в гугл).
*/
        //для этого надо удалить у двух из трех любых классов implements Flyable
        //Flyable flyable = context1.getBean(Flyable.class);
        //flyable.fly();
    }
}
