package com.degtyarenko.service;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


/**
 * The interface Car service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Validated
public interface CarService {

    /**
     * Find all cars.
     *
     * @return the list of cars
     */
    List<Car> findAll();

    @Transactional(readOnly = true)
    Page<Car> findAll(Integer pageNumber, Integer pageSize);

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
    Car create(@Valid CarDto carDto);

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
    Car update(@Valid CarDto carDto);

}
