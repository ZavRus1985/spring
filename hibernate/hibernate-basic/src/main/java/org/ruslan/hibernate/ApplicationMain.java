package org.ruslan.hibernate;

import org.ruslan.hibernate.config.AppConfig;
import org.ruslan.hibernate.entity.Car;
import org.ruslan.hibernate.repository.CarRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApplicationMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarRepository carRepository = applicationContext.getBean(CarRepository.class);

        Car car = new Car("Supra",1L, new BigDecimal("2002.22"), "Toyota", 2000, LocalDate.of(2022,12,11));
//        Car car1 = new Car(null, "Gelentwagen", new BigDecimal("1999.99"), "Mersedes", 2023, LocalDate.of(2024,1,1));
//        Car car2 = new Car(null, "Priora", new BigDecimal("1000.12"), "Lada", 2004, LocalDate.of(2023,10,9));
        carRepository.saveCar(car);
//        carRepository.saveCar(car1);
//        carRepository.saveCar(car2);
//
//        System.out.println("car by id");
//        carRepository.getCarById(2L).map(Car::getId);
//
//        System.out.println("car by filter");
//        carRepository.getCarByFilter(2000L).stream().map(Car::getId).forEach(System.out::println);
//
//        Car car4 = new Car(null, "CLS", new BigDecimal("4500.0"), "Mersedes", 2024, LocalDate.of(2024,9,9));
//        carRepository.updateCar(car4, 11L);

        carRepository.getAllCars().stream().map(Car::getModel).forEach(System.out::println);

 //       carRepository.deleteCar(5L);
 //       carRepository.getAllCars().stream().map(Car::getModel).forEach(System.out::println);
    }
}
