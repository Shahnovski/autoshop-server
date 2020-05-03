package com.example.autoshopserver.car;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.sql.Blob;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "brand.brandTitle", target = "brandTitle")
    CarDTO toCarDTO(Car car);

    List<CarDTO> toCarDTOs(List<Car> cars);

    Car toCar(CarDTO carDTO);

}
