package com.example.autoshopserver.car.page.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarPageRequest {
    Integer pageNumber;
    Integer pageSize;
    String sortByKey;
    String filterString;
}
