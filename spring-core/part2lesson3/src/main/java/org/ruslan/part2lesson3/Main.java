package org.ruslan.part2lesson3;

import org.ruslan.part2lesson3.config.Configuration;
import org.ruslan.part2lesson3.repository.CarRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Configuration.class);

        CarRepository carRepository = context.getBean(CarRepository.class);
//        carRepository.createTableCar();
//        Car car = new Car(null, "ferrari", new BigDecimal(0.01), "company", 1972);
//        Car car = new Car(null, "Moskvich", new BigDecimal(100.22), "ruslan", 1955);
//        carRepository.savePerson(car);
        System.out.println(carRepository.findAllClients());
    }
}
