package com.degtyarenko.service;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.dto.CarSaveDto;
import com.degtyarenko.entity.Car;

import java.util.List;


/**
 * The interface Car service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
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
     * @param id the id car
     * @return the car
     */
    Car findById(Long id);

    /**
     * Create car.
     *
     * @param carDto the car dto
     * @return the car
     */
    Car create(CarSaveDto carDto);

    /**
     * Delete.
     *
     * @param id the id car
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
