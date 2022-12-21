package com.degtyarenko.service;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.entity.CarCaseModel;

import java.util.List;


/**
 * The interface Car case model service.
 */
public interface CarCaseModelService {

    /**
     * Find all car case models.
     *
     * @return the list of car case models
     */
    List<CarCaseModel> findAll();

    /**
     * Find by id car case model.
     *
     * @param id the id
     * @return the car case model
     */
    CarCaseModel findById(Long id);

    /**
     * Create car case model.
     *
     * @param carCaseModelDto the car case model dto
     * @return the car case model
     */
    CarCaseModel create(CarCaseModelDto carCaseModelDto);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update car case model.
     *
     * @param carCaseModelDto the car case model dto
     * @return the car case model
     */
    CarCaseModel update(CarCaseModelDto carCaseModelDto);

}
