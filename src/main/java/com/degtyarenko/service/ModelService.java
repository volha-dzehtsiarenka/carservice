package com.degtyarenko.service;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.entity.Model;

import javax.validation.Valid;
import java.util.List;


/**
 * The interface Model service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
public interface ModelService {

    /**
     * Find all models.
     *
     * @return the list of models
     */
    List<Model> findAll();

    /**
     * Find by id model.
     *
     * @param id the id model
     * @return the model
     */
    Model findById(Long id);

    /**
     * Create model.
     *
     * @param modelDto the model dto
     * @return the model
     */
    Model create(@Valid ModelDto modelDto);

    /**
     * Delete.
     *
     * @param id the id model
     */
    void delete(Long id);

    /**
     * Update model.
     *
     * @param modelDto the model dto
     * @return the model
     */
    Model update(@Valid ModelDto modelDto);

}
