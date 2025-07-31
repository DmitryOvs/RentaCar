package by.chukotka.servicecar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "catalog", name = "t_car_rent")
public class CarRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_brand")
    private String brand;

    @Column(name = "c_model")
    private String model;

    @Column(name = "c_registration_number")
    private String registrationNumber;

    @Column(name = "c_seats")
    private short seats;

    @Column(name = "c_rent_cost")
    private int rentCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_type_car")
    private TypeCar type;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_gear")
    private Gear gear;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_fuel")
    private Fuel fuel;
}