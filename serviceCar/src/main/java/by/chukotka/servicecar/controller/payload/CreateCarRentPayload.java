package by.chukotka.servicecar.controller.payload;


import by.chukotka.servicecar.entity.Fuel;
import by.chukotka.servicecar.entity.Gear;
import by.chukotka.servicecar.entity.TypeCar;
import jakarta.validation.constraints.*;

public record CreateCarRentPayload(
        @NotNull(message = "{catalog.rentCars.create.errors.brand_is_null}")
        @Size(min = 2, max = 20, message = "{catalog.rentCars.create.errors.brand_length}")
        String brand,

        @NotNull(message = "{catalog.rentCars.create.errors.model_is_null}")
        @Size(min = 3, max = 50, message = "{catalog.rentCars.create.errors.model_size}")
        String model,

        @NotNull(message = "{catalog.rentCars.create.errors.registrationNumber_is_null}")
        @Pattern(regexp = "[а-я]\\d{3}[а-я]{2}\\d{2,3}",
                message = "{catalog.rentCars.create.errors.registrationNumber_is_not_format}")
        String registrationNumber,

        @Min(value = 2, message = "{catalog.rentCars.create.errors.seats_is_min}")
        @Max(value = 8, message = "{catalog.rentCars.create.errors.seats_is_max}")
        short seats,

        @Min(value = 50, message = "{catalog.rentCars.create.errors.rentCost_is_min}")
        int rentCost,

        TypeCar type,

        Gear gear,

        Fuel fuel)



{

}
