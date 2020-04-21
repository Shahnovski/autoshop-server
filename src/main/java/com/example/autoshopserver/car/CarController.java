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
    List<CarDTO> getCarList() {
        return carService.getCarList();
    }

    @GetMapping("/{id}")
    CarDTO getCarById(@PathVariable(value = "id") Long carId) {
        return carService.getCarById(carId);
    }

    @PostMapping("")
    CarDTO createCar(@Valid @RequestBody CarDTO carDTO) {
        return carService.saveCar(null, carDTO);
    }

    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable(value = "id") Long carId, @Valid @RequestBody CarDTO carDTO) {
        return carService.saveCar(carId, carDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable(value = "id") Long carId) {
        carService.deleteCar(carId);
    }

}
