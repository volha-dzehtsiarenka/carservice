package com.degtyarenko.service;

import com.degtyarenko.entity.BodyType;

import java.util.List;

public interface BodyTypeService {

    List<BodyType> findAll();

    BodyType findById(Long id);

    BodyType create(BodyType bodyType);

    void delete(Long id);

    BodyType update(BodyType bodyType);
}
