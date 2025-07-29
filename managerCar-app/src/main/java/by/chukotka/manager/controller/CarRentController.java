package by.chukotka.manager.controller;

import by.chukotka.manager.client.CarsRentRestClient;
import by.chukotka.manager.controller.payload.UpdateCarRentPayload;
import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.exeption.BadRequestException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping(("catalog/carsRent/{carId:\\d+}"))
public class CarRentController {

        private final CarsRentRestClient carsRentRestClient;
        private final MessageSource messageSource;

    @ModelAttribute("car")
        public CarRent car(@PathVariable("carId") int carId) {
            return this.carsRentRestClient.findCar(carId).orElseThrow(() -> new NoSuchElementException("catalog.errors.carNotFound"));
        }

        @GetMapping
        public String getCarRent() {
            return "catalog/carsRent/car";
        }

        @GetMapping("edit")
        public String getCarRentEdit() {
            return "catalog/carsRent/edit";
        }

        @PostMapping("edit")
        public String carRentEdit(@ModelAttribute(value = "car", binding = false) CarRent car, UpdateCarRentPayload payload,
                                   Model model) {
            try {
                this.carsRentRestClient.editCar(car.id(), payload.brand(), payload.model(), payload.registrationNumber(),
                        payload.seats(), payload.rentCost(), payload.type(), payload.gear(), payload.fuel());
                return "redirect:/catalog/carsRent/%d".formatted(car.id());
            } catch (BadRequestException exception) {
                model.addAttribute("payload", payload);
                model.addAttribute("errors", exception.getErrors());
                return "catalog/carsRent/edit";
            }
        }

        @PostMapping("delete")
        public String carRentDelete(@ModelAttribute("car") CarRent car) {
            this.carsRentRestClient.deleteCar(car.id());
            return "redirect:/catalog/carsRent/list";
        }

        @ExceptionHandler(NoSuchElementException.class)
        public String handlerNoSuchElementException(NoSuchElementException exception, Model model,
                                                    HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), new Object[0],
                exception.getMessage(), locale));
            return "errors/404";
        }
}
