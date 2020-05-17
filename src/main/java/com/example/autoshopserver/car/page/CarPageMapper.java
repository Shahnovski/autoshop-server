package com.example.autoshopserver.car.page;

import com.example.autoshopserver.car.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarPageMapper {

    CarPageDTO toCarPageDTO(CarPage carPage);

    List<Car> toCarPageDTOs(List<Car> carPages);

    CarPage toCarPage(CarPageDTO carPageDTO);
}
