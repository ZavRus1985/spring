package org.ruslan.hibernate.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ruslan.hibernate.utils.UpdateField;
import org.ruslan.hibernate.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CarRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Car> getAllCars() {

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            List<Car> cars = session.createQuery("from Car", Car.class)
                    .getResultList();
            session.getTransaction().commit();
            return cars;
        }
        catch (Exception ex) {

            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return List.of();
        }
    }
    public Optional<Car> getCarById(Long id) {

        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Car car = session.get(Car.class, id);

            session.getTransaction().commit();
            return Optional.ofNullable(car);

        }
        catch (Exception ex) {

            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Car> getCarByFilter(Long year) {

        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("from Car where year > :yearParam");
            query.setParameter("yearParam", year);
            List<Car> cars = query.list();

            session.getTransaction().commit();
            return cars;
        }
        catch (Exception ex) {

            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return List.of();
        }
    }

    public void saveCar(Car car) {

        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.persist(car);

            session.getTransaction().commit();
        }
        catch (Exception ex) {

            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
    }

    public void updateCar(Car car, Long id) {

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Car updatableCar = session.get(Car.class, id);
            if (updatableCar == null) {
                throw  new NoSuchElementException("No such car in table");
            }
//            updatableCar.setModel(car.getModel());
//            updatableCar.setPrice(car.getPrice());
//            updatableCar.setInsuranceExpirationDate(car.getInsuranceExpirationDate());
            UpdateField updateField = new UpdateField();
            updateField.updFields(updatableCar, car);
            session.persist(updatableCar);

            session.getTransaction().commit();
        }
        catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deleteCar(Long id) {

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Car deletableCar = session.get(Car.class, id);
            if (deletableCar == null) {
                throw  new NoSuchElementException("No such car in table");
            }
            session.remove(deletableCar);

            session.getTransaction().commit();
        }
        catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
    }




}
