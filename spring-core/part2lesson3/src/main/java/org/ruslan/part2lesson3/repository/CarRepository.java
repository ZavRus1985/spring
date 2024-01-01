package org.ruslan.part2lesson3.repository;

import org.ruslan.part2lesson3.Car;
import org.ruslan.part2lesson3.bean.JdbcConnection;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarRepository {

    private  final Connection connection;

    public CarRepository(Connection connection) {
        this.connection = connection;
    }

    public void createTableCar() {

        String sql = SqlFileReader.sqlQuerry();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate(sql);

        }
        catch (Exception ex ) {

            throw  new RuntimeException(ex);
        }
    }

    public List<Car> findAllClients() {

        String sql = "select * from cars";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Car> clients = new ArrayList<>();
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String model = resultSet.getString("model");
                BigDecimal price = resultSet.getBigDecimal("price");
                String owner = resultSet.getString("owner");
                Integer year = resultSet.getInt("year");

                Car car = new Car(id, model, price, owner, year);
                clients.add(car);
            }
            return clients;
        }
        catch (Exception ex ) {
            ex.printStackTrace();
            return List.of();
        }
    }

    public boolean savePerson(Car car) {


        String sql = "insert into cars values (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setNull(1, Types.NULL);
            statement.setString(2, car.getModel());
            statement.setBigDecimal(3, car.getPrice());
            statement.setString(4, car.getOwner());
            statement.setInt(5, car.getYear());


            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (Exception ex ) {

            throw  new RuntimeException(ex);
        }
    }
}
