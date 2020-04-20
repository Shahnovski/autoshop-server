package com.example.autoshopserver.car.brand;


import com.example.autoshopserver.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApplicationProperties.BRAND_API_URL)
public class BrandController {
    private final BrandService brandService;

    @GetMapping(value = "", produces = "application/json; charset=UTF-8")
    List<BrandDTO> getBrandList() {
        return brandService.getBrandList();
    }

    @GetMapping("/{id}")
    BrandDTO getBrandById(@PathVariable(value = "id") Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @PostMapping("")
    BrandDTO createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable(value = "id") Long brandId, @Valid @RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(brandId, brandDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable(value = "id") Long brandId) {
        brandService.deleteBrand(brandId);
    }
}
