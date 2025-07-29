package by.chukotka.servicecar.controller.payload;



import by.chukotka.servicecar.entity.CarRent;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record UpdateCarRentPayload(
        @NotNull(message = "{catalog.rentCars.update.errors.brand_is_null")
        @Length(min = 2, max = 20, message = "{catalog.rentCars.update.errors.brand_length}")
        String brand,

        @NotNull(message = "{catalog.rentCars.update.errors.model_is_null}")
        @Size(min = 3, max = 50, message = "{catalog.rentCars.update.errors.model_size}")
        String model,

        @NotNull(message = "{catalog.rentCars.update.errors.registrationNumber_is_null}")
        @Pattern(regexp = "[а-я]\\d{3}[а-я]{2}\\d{2,3}",
                message = "{catalog.rentCars.update.errors.registrationNumber_is_not_format}")
        String registrationNumber,

        @Min(value = 2, message = "{catalog.rentCars.update.errors.seats_is_min}")
        @Max(value = 8, message = "{catalog.rentCars.update.errors.seats_is_max}")
        short seats,

        @Min(value = 50, message = "{catalog.rentCars.update.errors.rentCost_is_min}")
        int rentCost,

        CarRent.TypeCar type,

        CarRent.Gear gear,

        CarRent.Fuel fuel)
{

}

