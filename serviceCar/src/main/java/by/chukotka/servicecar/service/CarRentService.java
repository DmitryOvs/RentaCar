package by.chukotka.servicecar.service;



import by.chukotka.servicecar.entity.CarRent;
import by.chukotka.servicecar.entity.Fuel;
import by.chukotka.servicecar.entity.Gear;
import by.chukotka.servicecar.entity.TypeCar;


import java.util.Optional;

public interface CarRentService {

    Iterable<CarRent> findAllCars(String filter);

    CarRent createCar(String brand, String model, String registrationNumber, short seats, int rentCost,
                      TypeCar type, Gear gear, Fuel fuel);

    Optional<CarRent> findCar(int carId);

    void editCar(Integer id, String brand, String model, String registrationNumber, short seats,
                 int rentCost, TypeCar type, Gear gear, Fuel fuel);

    void deleteCar(Integer id);

}


