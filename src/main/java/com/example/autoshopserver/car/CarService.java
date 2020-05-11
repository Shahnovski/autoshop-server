package com.example.autoshopserver.car;

import com.example.autoshopserver.auth.info.AuthInfoService;
import com.example.autoshopserver.car.brand.*;
import com.example.autoshopserver.exception.exceptions.CarNotFoundException;
import com.example.autoshopserver.exception.exceptions.UserNotFoundException;
import com.example.autoshopserver.user.Role;
import com.example.autoshopserver.user.User;
import com.example.autoshopserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
    private final AuthInfoService authInfoService;

    public List<CarDTO> getCarList(Authentication authentication) {
        return carMapper.toCarDTOs(carRepository.findAll());
    }

    public CarDTO getCarById(Long id, Authentication authentication) {
        Car car = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        return carMapper.toCarDTO(car);
    }

    public CarDTO saveCar(Long id, CarDTO carDTO, Authentication authentication) {
        Car car = carMapper.toCar(carDTO);
        if (id != null) {
            car.setId(id);
            User currentUser = authInfoService.getUserByAuthentication(authentication);
            User owner = userRepository.findById(carDTO.getOwnerId()).orElseThrow(UserNotFoundException::new);
            car.setOwner(owner);
            if (!car.getOwner().getId().equals(currentUser.getId()) && !currentUser.getRoles().contains(Role.ADMIN)) {
                throw new AccessDeniedException("The advertisement does not belong to the user");
            }
        }
        else {
            car.setOwner(authInfoService.getUserByAuthentication(authentication));
        }
        BrandDTO brandDTO = brandService.getBrandById(carDTO.getBrandId(), authentication);
        car.setBrand(brandMapper.toBrand(brandDTO));
        //User owner = userRepository.findById(carDTO.getOwnerId()).orElseThrow(UserNotFoundException::new);
        return carMapper.toCarDTO(carRepository.save(car));
    }
    

    public void deleteCar(Long carId, Authentication authentication) {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        User currentUser = authInfoService.getUserByAuthentication(authentication);
        if (!car.getOwner().getId().equals(currentUser.getId()) && !currentUser.getRoles().contains(Role.ADMIN)) {
            throw new AccessDeniedException("The advertisement does not belong to the user");
        }
        carRepository.deleteById(carId);
    }

}
