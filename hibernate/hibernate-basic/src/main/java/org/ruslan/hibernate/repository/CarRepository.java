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
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class CarRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CarRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Car> getAllCars() {

        Function<Session, List<Car>> getAllProducts = (session) -> {
            return session.createQuery("from Car", Car.class)
                    .getResultList();
        };
        return executeInTransaction(getAllProducts);
    }


    public Optional<Car> getCarById(Long id) {

        Function<Session, Optional<Car>> getCar = (session) -> {
            return session.createQuery("from Car", Car.class)
                    .getResultList().stream().findFirst();
        };
        return executeInTransaction(getCar);
    }

    public List<Car> getCarByFilter(Long year) {

        Function<Session, List<Car>> getCar = (session) -> {
            Query query = session.createQuery("from Car where year > :yearParam")
                    .setParameter("yearParam", year);
            List<Car> cars = query.list();
            return cars;
        };
        return executeInTransaction(getCar);
    }

    public void saveCar(Car car) {

        Consumer<Session> saveCar = (session) -> {
            session.persist(car);
        };
        executeInTransaction(saveCar);
    }

    public void updateCar(Car car, Long id) {

        Consumer<Session> updateCar = (session) -> {
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
        };
        executeInTransaction(updateCar);
    }

    public void deleteCar(Long id) {

        Consumer<Session> deletableCar = (session) -> {
            int rowsAffected = session.createMutationQuery("delete from Product where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        };
        executeInTransaction(deletableCar);
    }

    private void executeInTransaction(Consumer<Session> consumer){
        Function<Session, Void> func = (session -> {
            consumer.accept(session);
            return null;
        });
        executeInTransaction(func);
    }

    private <T> T executeInTransaction(Function<Session, T> func){
        Session session = null;
        try{
            session = sessionFactory.openSession();

            session.getTransaction().begin();

            T result = func.apply(session);

            session.getTransaction().commit();

            return result;
        }
        catch (Exception ex){
            if(session != null){
                session.getTransaction().rollback(); // 0/4 - success
            }
            throw new RuntimeException(ex);
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
}
