package by.chukotka.servicecar.controller;


import by.chukotka.servicecar.controller.payload.UpdateCarRentPayload;
import by.chukotka.servicecar.entity.CarRent;
import by.chukotka.servicecar.service.CarRentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;


import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-api/carsRent/{carId:\\d+}")
public class RESTControllerCarRent {

    private final CarRentService carRentService;
    private final MessageSource messageSource;

    @ModelAttribute("car")
    public CarRent getCarRent(@PathVariable("carId") int carId) {
        return this.carRentService.findCar(carId).orElseThrow(() -> new NoSuchElementException("catalog.errors.carNotFound"));
    }

    @GetMapping
    public CarRent findCarRent(@ModelAttribute("carId") CarRent car) {
        return car;
    }

    @PatchMapping
    public ResponseEntity<?> updateCarRent(@PathVariable("carId") int carId, @Valid @RequestBody UpdateCarRentPayload payload,
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.carRentService.editCar(carId, payload.brand(), payload.model(), payload.registrationNumber(),
                    payload.seats(), payload.rentCost(), payload.type(), payload.gear(), payload.fuel());
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCarRent(@PathVariable("carId") int carId) {
        this.carRentService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchException(NoSuchElementException exp, MessageSource messageSource, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        this.messageSource.getMessage(exp.getMessage(), new Object[0], exp.getMessage(), locale)));

    }
}
