package com.example.autoshopserver.car;

import com.example.autoshopserver.car.brand.*;
import com.example.autoshopserver.exception.exceptions.BrandNotFoundException;
import com.example.autoshopserver.exception.exceptions.CarNotFoundException;
import com.example.autoshopserver.exception.exceptions.UserNotFoundException;
import com.example.autoshopserver.user.User;
import com.example.autoshopserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BrandService brandService;
    private final UserRepository userRepository;
    private final BrandMapper brandMapper;

    public List<CarDTO> getCarList() {
        return carMapper.toCarDTOs(carRepository.findAll());
    }

    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        return carMapper.toCarDTO(car);
    }

    public CarDTO saveCar(Long id, CarDTO carDTO) {
        Car car = carMapper.toCar(carDTO);
        if (id != null) car.setId(id);
        BrandDTO brandDTO = brandService.getBrandById(carDTO.getBrandId());
        car.setBrand(brandMapper.toBrand(brandDTO));
        //User owner = userRepository.findById(carDTO.getOwnerId()).orElseThrow(UserNotFoundException::new);
        User owner = userRepository.findById(2L).orElseThrow(UserNotFoundException::new);
        car.setOwner(owner);
        return carMapper.toCarDTO(carRepository.save(car));
    }

    /*public CarDTO createCar(CarDTO carDTO) {
        Car car = carMapper.toCar(carDTO);
        BrandDTO brandDTO = brandService.getBrandById(carDTO.getBrandId());
        car.setBrand(brandMapper.toBrand(brandDTO));
        User owner = userRepository.findById(carDTO.getOwnerId()).orElseThrow(UserNotFoundException::new);
        car.setOwner(owner);
        return carMapper.toCarDTO(carRepository.save(car));
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car car = carMapper.toCar(carDTO);
        car.setId(id);
        BrandDTO brandDTO = brandService.getBrandById(carDTO.getBrandId());
        car.setBrand(brandMapper.toBrand(brandDTO));
        User owner = userRepository.findById(carDTO.getOwnerId()).orElseThrow(UserNotFoundException::new);
        car.setOwner(owner);
        return carMapper.toCarDTO(carRepository.save(car));
    }*/

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

}
