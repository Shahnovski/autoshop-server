package com.example.autoshopserver.car;

import com.example.autoshopserver.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApplicationProperties.CAR_API_URL)
public class CarController {
    private final CarService carService;

    @GetMapping(value = "", produces = "application/json; charset=UTF-8")
    List<Car> getCarList() {
        return carService.getCarList();
    }

    @GetMapping("/{id}")
    Car getCarById(@PathVariable(value = "id") Long carId) {
        return carService.getCarById(carId);
    }

    @PostMapping("")
    Car createCar(@Valid @RequestBody Car car) {
        return carService.createCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable(value = "id") Long carId, @Valid @RequestBody Car car) {
        return carService.updateCar(carId, car);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable(value = "id") Long carId) {
        carService.deleteCar(carId);
    }

}
