package by.chukotka.servicecar.service;


import by.chukotka.servicecar.entity.CarRent;
import by.chukotka.servicecar.entity.Fuel;
import by.chukotka.servicecar.entity.Gear;
import by.chukotka.servicecar.entity.TypeCar;
import by.chukotka.servicecar.repository.CarRentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarRentServiceImp implements CarRentService {

    private final CarRentRepository carRentRepository;


    @Override
    public Iterable<CarRent> findAllCars(String filter) {
        if (filter == null || filter.isEmpty()) {
        return this.carRentRepository.findAll();}
        else {
            return this.carRentRepository.findAllByBrandLikeIgnoreCase("%" + filter + "%");
        }
    }

    @Override
    @Transactional
    public CarRent createCar(String brand, String model, String registrationNumber, short seats, int rentCost,
                             TypeCar type, Gear gear, Fuel fuel) {
        return this.carRentRepository.save(new CarRent(null, brand, model, registrationNumber, seats, rentCost, type, gear, fuel));
    }

    @Override
    public Optional<CarRent> findCar(int carId) {
        return this.carRentRepository.findById(carId);
    }

    @Override
    @Transactional
    public void editCar(Integer carId, String brand, String model, String registrationNumber, short seats,
                        int rentCost, TypeCar type, Gear gear, Fuel fuel) {
        this.carRentRepository.findById(carId).ifPresentOrElse(car -> {
            car.setBrand(brand);
            car.setModel(model);
            car.setRegistrationNumber(registrationNumber);
            car.setSeats(seats);
            car.setRentCost(rentCost);
            car.setType(type);
            car.setGear(gear);
            car.setFuel(fuel);
        }, () -> {throw new NoSuchElementException("No car found with id " + carId);});
    }

    @Override
    @Transactional
    public void deleteCar(Integer id) {
        this.carRentRepository.deleteById(id);
    }
}
