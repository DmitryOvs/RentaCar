package by.chukotka.manager.controller;

import by.chukotka.manager.controller.payload.CreateCarRentPayload;
import by.chukotka.manager.entity.CarRent;
import by.chukotka.manager.service.CarRentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/carsRent")
public class ProductsController {

    private final CarRentService productSer;
    //  @RequestMapping(value = "list", method = RequestMethod.GET) - тоже что и ниже
    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("cars", this.productSer.findAllCars());
        return "catalog/carsRent/list";
    }

    @GetMapping("create")
    public String getNewProductPage(Model model) {
        return "catalog/carsRent/new_car";
    }

    @PostMapping("create")
    public String createProduct(@Valid CreateCarRentPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalog/carsRent/new_car";
        }else {
        CarRent car = this.productSer.createCar(payload.brand(), payload.model(), payload.registrationNumber(),
                payload.seats(), payload.rentCost(), payload.type(), payload.gear(), payload.fuel());
        return "redirect:%d".formatted(car.getId());
        }
    }
}
