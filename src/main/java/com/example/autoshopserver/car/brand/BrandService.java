package com.example.autoshopserver.car.brand;

import com.example.autoshopserver.car.Car;
import com.example.autoshopserver.car.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> getBrandList() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand brand) {
        brand.setId(id);
        return brandRepository.save(brand);
    }

    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}
