package com.degtyarenko.service;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.entity.CarCaseModel;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


/**
 * The interface Car case model service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Validated
public interface CarCaseModelService {

    /**
     * Find all car case models.
     *
     * @return the list of car case models
     */
    List<CarCaseModel> findAll();

    @Transactional(readOnly = true)
    Page<CarCaseModel> findAll(Integer pageNumber, Integer pageSize);

    /**
     * Find by id car case model.
     *
     * @param id the id car case model
     * @return the car case model
     */
    CarCaseModel findById(Long id);

    /**
     * Create car case model.
     *
     * @param carCaseModelDto the car case model dto
     * @return the car case model
     */
    CarCaseModel create(@Valid CarCaseModelDto carCaseModelDto);

    /**
     * Delete.
     *
     * @param id the id car case model
     */
    void delete(Long id);

    /**
     * Update car case model.
     *
     * @param carCaseModelDto the car case model dto
     * @return the car case model
     */
    CarCaseModel update(@Valid CarCaseModelDto carCaseModelDto);

}
