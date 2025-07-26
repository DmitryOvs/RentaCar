package by.chukotka.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRent {

    private Integer id;

    private String brand;

    private String model;

    private String registrationNumber;

    private short seats;

    private int rentCost;

    private TypeCar type;

    private Gear gear;

    private Fuel fuel;



    public enum TypeCar {
        SUV, SPORTS_CAR, COUPE, MINIVAN, CONVERTIBLE, HATCHBACK, SEDAN, PICKUP_TRUCK
    }

    public enum Gear {
        Automatic, Manual
    }

    public enum Fuel {
        Diesel, Petrol
    }
}




   /* private Integer id;
    private String title;
    private String details;*/

