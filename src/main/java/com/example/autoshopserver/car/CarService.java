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
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BrandService brandService;
    private final UserRepository userRepository;
    private final AuthInfoService authInfoService;
    private final CarMapper carMapper;
    private final BrandMapper brandMapper;
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
            Object filterValue = filterJsonArray.getJSONArray(i).get(1).toString();
            String operation = ":";

            if (filterKey.compareTo("typeTransmission") == 0) {
                filterValue = TypeTransmission.valueOf((String) filterValue);
            }

            else if (filterKey.compareTo("typeEngine") == 0) {
                filterValue = TypeEngine.valueOf((String) filterValue);
            }

            else if (filterKey.compareTo("carCost") == 0) {
                operation = ">";
            }

            else if (filterKey.compareTo("owner") == 0) {
                filterValue = user;
            }

            else if (filterKey.compareTo("brand") == 0) {
                filterValue = brandMapper.toBrand(brandService.getBrandById(Long.parseLong((String)filterValue), authentication));
            }

            carSpecifications[i] = new CarSpecification(new SearchCriteria(filterKey, operation, (Object) filterValue));
            if (specification == null) specification = Specification.where(carSpecifications[i]);
            else specification = specification.and(carSpecifications[i]);
        }

        CarSpecification isDeleteSpecification = new CarSpecification(new SearchCriteria("isDeleted",":",false));
        if (specification == null) specification = Specification.where(isDeleteSpecification);
        else specification = specification.and(isDeleteSpecification);

        Page page = carRepository.findAll(specification, pageable);
        CarPage carPage = CarPage.builder()
                .code(200)
                .status("ok")
                .cars(carMapper.toCarDTOs(page.getContent()))
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
            User owner = userRepository.findById(carDTO.getOwnerId()).orElseThrow(UserNotFoundException::new);
            car.setOwner(owner);
            if (!allowEditCars(authentication, car)) {
                throw new AccessDeniedException("The advertisement does not belong to the user");
            }
        }
        else {
            car.setOwner(authInfoService.getUserByAuthentication(authentication));
        }
        car.setIsDeleted(false);
        BrandDTO brandDTO = brandService.getBrandById(carDTO.getBrandId(), authentication);
        car.setBrand(brandMapper.toBrand(brandDTO));
        return carMapper.toCarDTO(carRepository.save(car));
    }
    

    public void deleteCar(Long carId, Authentication authentication) {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        if (!allowEditCars(authentication, car)) {
            throw new AccessDeniedException("The advertisement does not belong to the user");
        }
        car.setIsDeleted(true);
        carRepository.save(car);
    }

    public boolean allowEditCars(Authentication authentication, Car car) {
        User currentUser = authInfoService.getUserByAuthentication(authentication);
        return car.getOwner().getId().equals(currentUser.getId()) || currentUser.getRoles().contains(Role.ADMIN);
    }

}
