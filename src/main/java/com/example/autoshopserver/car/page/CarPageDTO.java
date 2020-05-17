package com.example.autoshopserver.car.page;

import com.example.autoshopserver.car.CarDTO;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPageDTO {
    String status;
    Integer code;
    List<CarDTO> cars;
    Long totalElements;
    Integer totalPages;
}

