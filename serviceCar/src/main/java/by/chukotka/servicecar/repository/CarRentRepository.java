package by.chukotka.servicecar.repository;



import by.chukotka.servicecar.entity.CarRent;

import java.util.List;
import java.util.Optional;

public interface CarRentRepository {

    List<CarRent> findAll();

    CarRent save(CarRent car);

    Optional<CarRent> findById(Integer carId);

    void deleteById(Integer id);
}
