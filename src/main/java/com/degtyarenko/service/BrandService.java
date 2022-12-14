package com.degtyarenko.service;

import com.degtyarenko.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findById(Long id);


    Brand create(Brand brand);

    void delete(Long id);

    Brand update(Brand brand);
}
