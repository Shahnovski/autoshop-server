package com.example.autoshopserver.car.page.request;

import com.example.autoshopserver.car.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarPageRequestMapper {

    @Mapping(source = "page", target = "pageNumber")
    @Mapping(source = "size", target = "pageSize")
    @Mapping(source = "sort", target = "sortByKey")
    @Mapping(source = "filter", target = "filterString")
    CarPageRequest toCarPageRequest(CarPageRequestDTO carPageRequestDTO);

    CarPageRequestDTO toCarPageRequestDTO(CarPageRequest carPageRequest);

    List<Car> toCarPageRequestDTOs(List<Car> carPageRequests);

}
