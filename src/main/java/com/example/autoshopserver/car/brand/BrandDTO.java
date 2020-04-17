package com.example.autoshopserver.car.brand;

import com.example.autoshopserver.car.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long id;
    private String brandTitle;
    private List<Car> cars;

}
