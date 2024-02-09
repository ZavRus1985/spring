package org.ruslan.hibernate.utils;

import org.ruslan.hibernate.entity.Car;

public class UpdateField {

     public Car updFields (Car updatableCar, Car newValueCar)  {

        updatableCar.setModel(newValueCar.getModel());
        updatableCar.setPrice(newValueCar.getPrice());
        updatableCar.setInsuranceExpirationDate(newValueCar.getInsuranceExpirationDate());
        return updatableCar;
    }
}
