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

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car;
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

}
