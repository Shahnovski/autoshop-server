package com.example.autoshopserver.Car;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, String> {
    List<Car> findById(Long id);
    List<Car> findAll();
}
