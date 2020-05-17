package com.example.autoshopserver.car;

import com.example.autoshopserver.auth.info.AuthInfoService;
import com.example.autoshopserver.car.brand.*;
import com.example.autoshopserver.car.page.CarPage;
import com.example.autoshopserver.car.page.CarPageDTO;
import com.example.autoshopserver.car.page.CarPageMapper;
import com.example.autoshopserver.car.page.request.CarPageRequest;
import com.example.autoshopserver.car.page.request.CarPageRequestDTO;
import com.example.autoshopserver.car.page.request.CarPageRequestMapper;
import com.example.autoshopserver.common.SearchCriteria;
import com.example.autoshopserver.exception.exceptions.CarNotFoundException;
import com.example.autoshopserver.exception.exceptions.UserNotFoundException;
import com.example.autoshopserver.user.Role;
import com.example.autoshopserver.user.User;
import com.example.autoshopserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private final CarPageRequestMapper carPageRequestMapper;
    private final CarPageMapper carPageMapper;

    public CarPageDTO getCarList(CarPageRequestDTO carPageRequestDTO, Authentication authentication) {
        CarPageRequest carPageRequest = carPageRequestMapper.toCarPageRequest(carPageRequestDTO);
        JSONArray filterJsonArray = new JSONArray(carPageRequest.getFilterString());

        Pageable pageable = PageRequest.of(
                carPageRequest.getPageNumber(),
                carPageRequest.getPageSize(),
                Sort.by(carPageRequest.getSortByKey())
        );

        User user = authInfoService.getUserByAuthentication(authentication);

        CarSpecification[] carSpecifications = new CarSpecification[filterJsonArray.length()];
        Specification specification = null;

        for (int i = 0; i < filterJsonArray.length(); i++) {
            String filterKey = filterJsonArray.getJSONArray(i).get(0).toString();
            String filterValue = filterJsonArray.getJSONArray(i).get(1).toString();
            String operation = ":";

            if (filterKey.compareTo("carCost") == 0) {
                operation = ">";
            }

            carSpecifications[i] = new CarSpecification(new SearchCriteria(filterKey, operation, (Object) filterValue));
            if (specification == null) specification = Specification.where(carSpecifications[i]);
            else specification = specification.and(carSpecifications[i]);
        }

        Page page = carRepository.findAll(specification, pageable);
        CarPage carPage = CarPage.builder()
                .code(200)
                .status("ok")
                .cars(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();

        return carPageMapper.toCarPageDTO(carPage);
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
