package by.chukotka.manager.service;

import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.repository.CarRentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarRentServiceImp implements CarRentService {

    private final CarRentRepository carRentRepository;


    @Override
    public List<CarRent> findAllCars() {
        return this.carRentRepository.findAll();
    }

    @Override
    public CarRent createCar(String brand, String model, String registrationNumber, short seats, int rentCost,
                             CarRent.TypeCar type, CarRent.Gear gear, CarRent.Fuel fuel) {
        return this.carRentRepository.save(new CarRent(null, brand, model, registrationNumber, seats, rentCost, type, gear, fuel));
    }

    @Override
    public Optional<CarRent> findCar(int carId) {
        return this.carRentRepository.findById(carId);
    }

    @Override
    public void editCar(Integer id, String brand, String model, String registrationNumber, short seats,
                        int rentCost, CarRent.TypeCar type, CarRent.Gear gear, CarRent.Fuel fuel) {
        this.carRentRepository.findById(id).ifPresentOrElse(car -> {
            car.setBrand(brand);
            car.setModel(model);
            car.setRegistrationNumber(registrationNumber);
            car.setSeats(seats);
            car.setRentCost(rentCost);
            car.setType(type);
            car.setGear(gear);
            car.setFuel(fuel);
        }, () -> {throw new NoSuchElementException("No car found with id " + id);});
    }

    @Override
    public void deleteCar(Integer id) {
        this.carRentRepository.deleteById(id);
    }
}
