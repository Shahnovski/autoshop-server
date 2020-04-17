package com.example.autoshopserver.car;

import com.example.autoshopserver.car.brand.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.autoshopserver.car.brand.Brand;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BrandService brandService;

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car car) {
        car.setId(id);
        return carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

}
