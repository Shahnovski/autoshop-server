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

    public Optional<Brand> getBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand;
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}
