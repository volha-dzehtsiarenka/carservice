package com.degtyarenko.service;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;

import javax.validation.Valid;
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
    Brand create(@Valid BrandDto brandDto);

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
    Brand update(@Valid BrandDto brandDto);

}
