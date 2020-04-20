package com.example.autoshopserver.car.brand;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDTO toBrandDTO(Brand brand);

    List<BrandDTO> toBrandDTOs(List<Brand> brands);

    Brand toBrand(BrandDTO brandDTO);

}
