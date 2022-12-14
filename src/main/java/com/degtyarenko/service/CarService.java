package com.degtyarenko.service;

import com.degtyarenko.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car findById(Long id);

    Car create(Car car);

    void delete(Long id);

    Car update(Car car);
}
