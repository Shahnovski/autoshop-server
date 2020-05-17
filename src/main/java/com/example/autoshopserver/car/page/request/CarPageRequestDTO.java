package com.example.autoshopserver.car.page.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarPageRequestDTO {
    Integer page;
    Integer size;
    String sort;
    String filter;
}
