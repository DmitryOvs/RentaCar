package by.chukotka.manager.controller;

import by.chukotka.manager.client.CarsRentRestClient;
import by.chukotka.manager.controller.payload.CreateCarRentPayload;
import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/carsRent")
public class CarsRentController {

    private final CarsRentRestClient carsRentRestClient;

    //  @RequestMapping(value = "list", method = RequestMethod.GET) - тоже что и ниже
    @GetMapping("list")
    public String getCarsRentList(Model model, @RequestParam (name = "filter" , required = false) String filter,
                                  Principal principal) {
        LoggerFactory.getLogger(CarRent.class).info("User {}", principal); // просто вывод информации об авторизированном пользователе
        model.addAttribute("cars", this.carsRentRestClient.findAllCars(filter));
        model.addAttribute("filter", filter);
        return "catalog/carsRent/list";
    }
    @GetMapping("create")
    public String getNewCarRentPage() {
        return "catalog/carsRent/new_car";
    }

    @PostMapping("create")
    public String createCarRent(CreateCarRentPayload payload, Model model) {
      try{
        CarRent car = this.carsRentRestClient.createCar(payload.brand(), payload.model(), payload.registrationNumber(),
                payload.seats(), payload.rentCost(), payload.type(), payload.gear(), payload.fuel());
        return "redirect:/catalog/carsRent/%d".formatted(car.id());
        } catch (BadRequestException exception) {
          model.addAttribute("payload", payload);
          model.addAttribute("errors", exception.getErrors());
          return "catalog/carsRent/new_car";
      }
    }
}
