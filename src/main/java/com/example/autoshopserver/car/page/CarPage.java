package com.example.autoshopserver.car.page;

import com.example.autoshopserver.car.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPage {
    String status;
    Integer code;
    List<Car> cars;
    Long totalElements;
    Integer totalPages;
}
