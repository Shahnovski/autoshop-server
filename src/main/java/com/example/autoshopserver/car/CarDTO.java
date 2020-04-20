package com.example.autoshopserver.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Long id;
    private Long brandId;
    private Long ownerId;
    private String carModel;
    private TypeTransmission typeTransmission;
    private TypeEngine typeEngine;
    private Double mileageCar;
    private String carStatus;
    private Float carCost;
    private String countryLocation;
    private String cityLocation;

}
