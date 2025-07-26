package by.chukotka.manager.repository;

import by.chukotka.manager.entity.CarRent;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

import static by.chukotka.manager.entity.CarRent.Fuel.Petrol;
import static by.chukotka.manager.entity.CarRent.Gear.Manual;
import static by.chukotka.manager.entity.CarRent.TypeCar.SUV;

@Repository
public class InMemoryCarRentRepository implements CarRentRepository {

    private final List<CarRent> cars = Collections.synchronizedList(new LinkedList<>()); //многопоточное приложение поэтому и коллекция

    public InMemoryCarRentRepository() {      // Просто для проверки добавляем элементы
        IntStream.range(1,4)
                .forEach(i -> {this.cars.add(new CarRent(i, "Бренд автомобиль №%d".formatted(i),
                        "Модель автомобиля №%d".formatted(i), "Регистрационный номер автомобиля №%d".formatted(i),
                        (short)(2+i), 50+2*i, SUV, Manual, Petrol));});

    }

    @Override
    public List<CarRent> findAll() {
        return Collections.unmodifiableList(this.cars); //неизменяемая коллекция
    }

    @Override
    public CarRent save(CarRent car) {
        car.setId(this.cars.stream()
                .max(Comparator.comparingInt(CarRent::getId))
                .map(CarRent::getId)
                .orElse(0)+1);
        this.cars.add(car);
        return car;
    }

    @Override
    public Optional<CarRent> findById(Integer carId) {
        return this.cars.stream()
                .filter(car -> Objects.equals(carId, car.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        this.cars.removeIf(car -> Objects.equals(car.getId(), id));
    }
}
