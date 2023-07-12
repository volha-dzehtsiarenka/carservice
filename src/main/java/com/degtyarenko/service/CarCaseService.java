package com.degtyarenko.service;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.entity.CarCase;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


/**
 * The interface Car case service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Validated
public interface CarCaseService {

    /**
     * Find all car cases.
     *
     * @return the list of car cases
     */
    List<CarCase> findAll();

    @Transactional(readOnly = true)
    Page<CarCase> findAll(Integer pageNumber, Integer pageSize);

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
    CarCase create(@Valid CarCaseDto carCaseDto);

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
    CarCase update(@Valid CarCaseDto carCaseDto);

}
