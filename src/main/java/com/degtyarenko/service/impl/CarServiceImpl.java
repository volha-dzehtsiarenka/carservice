package com.degtyarenko.service.impl;

import com.degtyarenko.entity.Car;
import com.degtyarenko.exeption.NotFoundException;
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
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Car not found"));
    }

    @Override
    @Transactional
    public Car create(Car car) {
        return repository.save(car);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else throw new NotFoundException("Car not found");
    }

    @Override
    @Transactional
    public Car update(Car car) {
        return create(car);
    }

}
