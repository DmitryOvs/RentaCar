package by.chukotka.servicecar.controller;


import by.chukotka.servicecar.controller.payload.CreateCarRentPayload;
import by.chukotka.servicecar.entity.CarRent;
import by.chukotka.servicecar.service.CarRentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-api/carsRent")
public class RESTControllerCarsRent {

    private final CarRentService carRentService;

    @GetMapping
    public Iterable<CarRent> getCarRents(@RequestParam (name = "filter", required = false) String filter) {
        return this.carRentService.findAllCars(filter);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)//можно не писать
    public ResponseEntity<?> createCarRent(@Valid @RequestBody CreateCarRentPayload payload,
                                                 BindingResult bindingResult, UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            }else {
                throw new BindException(bindingResult);
            }
        }else {
            CarRent car = this.carRentService.createCar(payload.brand(), payload.model(), payload.registrationNumber(),
                    payload.seats(), payload.rentCost(), payload.type(), payload.gear(), payload.fuel());
            return ResponseEntity.created(uriBuilder
                    .replacePath("/catalog-api/carsRent/{carId}")
                    .build(Map.of("carId", car.getId())))
                    .body(car);
        }

    }
}
