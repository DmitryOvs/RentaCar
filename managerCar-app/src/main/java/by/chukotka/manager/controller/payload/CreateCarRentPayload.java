package by.chukotka.manager.controller.payload;


import by.chukotka.manager.entity.Fuel;
import by.chukotka.manager.entity.Gear;
import by.chukotka.manager.entity.TypeCar;

public record CreateCarRentPayload(
        String brand,
        String model,
        String registrationNumber,
        short seats,
        int rentCost,
        TypeCar type,
        Gear gear,
        Fuel fuel)



{

}
