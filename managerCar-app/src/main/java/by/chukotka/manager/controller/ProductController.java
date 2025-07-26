package by.chukotka.manager.controller;

import by.chukotka.manager.controller.payload.UpdateCarRentPayload;
import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.service.CarRentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping(("catalog/carsRent/{carId:\\d+}"))
public class ProductController {

        private final CarRentService carRentService;
        private final MessageSource messageSource;

    @ModelAttribute("car")
        public CarRent carRent(@PathVariable("carId") int carId) {
            return this.carRentService.findCar(carId).orElseThrow(() -> new NoSuchElementException("catalog.errors.carNotFound"));
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
        public String carRentEdit(@ModelAttribute(value = "car", binding = false) CarRent car, @Valid UpdateCarRentPayload payload,
                                  BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("payload", payload);
                model.addAttribute("errors", bindingResult.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList());
                return "catalog/carsRent/edit";
            }else{
                this.carRentService.editCar(car.getId(), payload.brand(), payload.model(), payload.registrationNumber(),
                        payload.seats(), payload.rentCost(), payload.type(), payload.gear(), payload.fuel());
                return "redirect:/catalog/carsRent/%d".formatted(car.getId());
            }
        }

        @PostMapping("delete")
        public String productDelete(@ModelAttribute("car") CarRent product) {
            this.carRentService.deleteCar(product.getId());
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
