package com.example.autoshopserver.car.brand;

import com.example.autoshopserver.exception.exceptions.BrandNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandDTO> getBrandList() {
        return brandMapper.toBrandDTOs(brandRepository.findAll());
    }

    public BrandDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        return brandMapper.toBrandDTO(brand);
    }
    
    public BrandDTO saveBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandMapper.toBrand(brandDTO);
        if (id != null) brand.setId(id);
        return brandMapper.toBrandDTO(brandRepository.save(brand));
    }

    /*public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = brandMapper.toBrand(brandDTO);
        return brandMapper.toBrandDTO(brandRepository.save(brand));
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandMapper.toBrand(brandDTO);
        brand.setId(id);
        return brandMapper.toBrandDTO(brandRepository.save(brand));
    }*/

    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}
