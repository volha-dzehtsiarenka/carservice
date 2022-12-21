package com.degtyarenko.service;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;

import java.util.List;


/**
 * The interface Car service.
 */
public interface CarService {

    /**
     * Find all cars.
     *
     * @return the list of cars
     */
    List<Car> findAll();

    /**
     * Find by id car.
     *
     * @param id the id
     * @return the car
     */
    Car findById(Long id);

    /**
     * Create car.
     *
     * @param carDto the car dto
     * @return the car
     */
    Car create(CarDto carDto);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update car.
     *
     * @param carDto the car dto
     * @return the car
     */
    Car update(CarDto carDto);

}
