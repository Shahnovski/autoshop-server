package com.example.autoshopserver.car.brand;


import com.example.autoshopserver.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(ApplicationProperties.BRAND_API_URL)
public class BrandController {
    private final BrandService brandService;

    @GetMapping(value = "", produces = "application/json; charset=UTF-8")
    List<Brand> getBrandList() {
        return brandService.getBrandList();
    }

    @GetMapping("/{id}")
    Brand getBrandById(@PathVariable(value = "id") Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @PostMapping("")
    Brand createBrand(@Valid @RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }

    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable(value = "id") Long brandId, @Valid @RequestBody Brand brand) {
        return brandService.updateBrand(brandId, brand);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable(value = "id") Long brandId) {
        brandService.deleteBrand(brandId);
    }
}
