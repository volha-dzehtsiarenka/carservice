package com.degtyarenko.service;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.entity.Model;

import java.util.List;


/**
 * The interface Model service.
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
     * @param id the id
     * @return the model
     */
    Model findById(Long id);

    /**
     * Create model.
     *
     * @param modelDto the model dto
     * @return the model
     */
    Model create(ModelDto modelDto);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update model.
     *
     * @param modelDto the model dto
     * @return the model
     */
    Model update(ModelDto modelDto);

}
