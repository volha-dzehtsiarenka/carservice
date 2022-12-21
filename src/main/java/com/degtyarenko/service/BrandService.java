package com.degtyarenko.service;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;

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
     * @param id the id
     * @return the brand
     */
    Brand findById(Long id);

    /**
     * Create brand.
     *
     * @param brandDto the brand dto
     * @return the brand
     */
    Brand create(BrandDto brandDto);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update brand.
     *
     * @param brandDto the brand dto
     * @return the brand
     */
    Brand update(BrandDto brandDto);

}
