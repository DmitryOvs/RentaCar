package by.chukotka.manager.client;

import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.entity.Fuel;
import by.chukotka.manager.entity.Gear;
import by.chukotka.manager.entity.TypeCar;

import java.util.List;
import java.util.Optional;

public interface CarsRentRestClient {

    List<CarRent> findAllCars();

    CarRent createCar(String brand, String model, String registrationNumber, short seats, int rentCost,
                      TypeCar type, Gear gear, Fuel fuel);

    Optional<CarRent> findCar(int carId);

    void editCar(Integer id, String brand, String model, String registrationNumber, short seats,
                 int rentCost, TypeCar type, Gear gear, Fuel fuel);

    void deleteCar(int id);
}
