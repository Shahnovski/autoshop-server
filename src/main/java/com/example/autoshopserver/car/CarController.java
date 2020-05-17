package com.example.autoshopserver.car;

import com.example.autoshopserver.car.page.CarPage;
import com.example.autoshopserver.car.page.CarPageDTO;
import com.example.autoshopserver.car.page.request.CarPageRequestDTO;
import com.example.autoshopserver.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApplicationProperties.CAR_API_URL)
public class CarController {
    private final CarService carService;

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "", produces = "application/json; charset=UTF-8")
    CarPageDTO getCarList(CarPageRequestDTO carPageRequestDTO, Authentication authentication) {
        return carService.getCarList(carPageRequestDTO, authentication);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/{id}")
    CarDTO getCarById(@PathVariable(value = "id") Long carId, Authentication authentication) {
        return carService.getCarById(carId, authentication);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @PostMapping("")
    CarDTO createCar(@Valid @RequestBody CarDTO carDTO, Authentication authentication) {
        return carService.saveCar(null, carDTO, authentication);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable(value = "id") Long carId, @Valid @RequestBody CarDTO carDTO, Authentication authentication) {
        return carService.saveCar(carId, carDTO, authentication);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable(value = "id") Long carId, Authentication authentication) {
        carService.deleteCar(carId, authentication);
    }

}
