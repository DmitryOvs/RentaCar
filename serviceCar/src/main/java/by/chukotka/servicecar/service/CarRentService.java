package by.chukotka.servicecar.service;



import by.chukotka.servicecar.entity.CarRent;

import java.util.List;
import java.util.Optional;

public interface CarRentService {

    List<CarRent> findAllCars();

    CarRent createCar(String brand, String model, String registrationNumber, short seats, int rentCost,
                      CarRent.TypeCar type, CarRent.Gear gear, CarRent.Fuel fuel);

    Optional<CarRent> findCar(int carId);

    void editCar(Integer id, String brand, String model, String registrationNumber, short seats,
                 int rentCost, CarRent.TypeCar type, CarRent.Gear gear, CarRent.Fuel fuel);

    void deleteCar(Integer id);

}


