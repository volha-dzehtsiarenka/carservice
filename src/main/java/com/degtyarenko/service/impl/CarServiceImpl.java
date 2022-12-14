package com.degtyarenko.service.impl;

import com.degtyarenko.entity.Car;
import com.degtyarenko.repository.CarRepository;
import com.degtyarenko.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return repository.findById(id).orElse(new Car());
    }

    @Override
    @Transactional
    public Car create(Car car) {
        return repository.save(car);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id);
    }

    @Override
    @Transactional
    public Car update(Car car) {
        return repository.save(car);
    }
}
