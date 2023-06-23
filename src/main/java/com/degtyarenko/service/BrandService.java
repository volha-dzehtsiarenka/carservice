package com.degtyarenko.service;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.exeption.EntityNotFoundException;

import java.util.List;


/**
 * The interface Brand service.
 */
public interface BrandService {


    /**
     * Find all brands.
     *
     * @return the list of brands
     */
    List<Brand> findAll();

    /**
     * Find by id brand.
     *
     * @param id the id brand
     * @return the brand
     */
    Brand findById(Long id);

    /**
     * Create brand.
     *
     * @param brandDto the brand dto
     * @return the brand
     */
    Brand create(BrandSaveDto brandDto);

    /**
     * Delete.
     *
     * @param id the id brand
     */
    void delete(Long id);

    /**
     * Update brand.
     *
     * @param brandDto the brand dto
     * @return the brand
     */
    Brand update(BrandDto brandDto) throws EntityNotFoundException;

}
