package by.chukotka.manager.entity;


public record CarRent(Integer id, String brand, String model, String registrationNumber, short seats, int rentCost,
                       TypeCar type, Gear gear, Fuel fuel) {
}



