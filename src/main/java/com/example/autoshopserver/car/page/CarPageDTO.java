package com.example.autoshopserver.car.page;

import com.example.autoshopserver.car.Car;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPageDTO {
    String status;
    Integer code;
    List<Car> cars;
    Long totalElements;
    Integer totalPages;
}

