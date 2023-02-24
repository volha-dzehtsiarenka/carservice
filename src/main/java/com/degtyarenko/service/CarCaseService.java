package com.degtyarenko.service;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.dto.CarCaseSaveDto;
import com.degtyarenko.entity.CarCase;

import java.util.List;


/**
 * The interface Car case service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
public interface CarCaseService {

    /**
     * Find all car cases.
     *
     * @return the list of car cases
     */
    List<CarCase> findAll();

    /**
     * Find by id car case.
     *
     * @param id the id car case
     * @return the car case
     */
    CarCase findById(Long id);

    /**
     * Create car case.
     *
     * @param carCaseDto the car case dto
     * @return the car case
     */
    CarCase create(CarCaseSaveDto carCaseDto);

    /**
     * Delete.
     *
     * @param id the id car case
     */
    void delete(Long id);

    /**
     * Update car case.
     *
     * @param carCaseDto the car case dto
     * @return the car case
     */
    CarCase update(CarCaseDto carCaseDto);

}
