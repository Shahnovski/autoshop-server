package com.example.autoshopserver.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brandId")
    private Long brandId;

    @Column(name = "carModel")
    private String carModel;

    @Column(name = "typeTransmission")
    private TypeTransmission typeTransmission;

    @Column(name = "typeEngine")
    private TypeEngine typeEngine;

    @Column(name = "carMileage")
    private Double mileageCar;

    @Column(name = "carStatus")
    private String carStatus;

    @Column(name = "carCost")
    private Float carCost;

    @Column(name = "ownerCountry")
    private String ownerCountry;

    @Column(name = "ownerCity")
    private String ownerCity;

}
