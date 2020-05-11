package com.example.autoshopserver.car.brand;


import com.example.autoshopserver.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApplicationProperties.BRAND_API_URL)
public class BrandController {
    private final BrandService brandService;

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping(value = "", produces = "application/json; charset=UTF-8")
    List<BrandDTO> getBrandList(Authentication authentication) {
        return brandService.getBrandList( authentication);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/{id}")
    BrandDTO getBrandById(@PathVariable(value = "id") Long brandId, Authentication authentication) {
        return brandService.getBrandById(brandId, authentication);
    }

    @RolesAllowed("ADMIN")
    @PostMapping("")
    BrandDTO createBrand(@Valid @RequestBody BrandDTO brandDTO, Authentication authentication) {
        return brandService.saveBrand(null, brandDTO, authentication);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable(value = "id") Long brandId, @Valid @RequestBody BrandDTO brandDTO, Authentication authentication) {
        return brandService.saveBrand(brandId, brandDTO, authentication);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable(value = "id") Long brandId, Authentication authentication) {
        brandService.deleteBrand(brandId, authentication);
    }
}
