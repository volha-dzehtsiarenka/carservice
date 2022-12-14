package com.degtyarenko.service;

import com.degtyarenko.entity.Model;

import java.util.List;

public interface ModelService {

    List<Model> findAll();

    Model findById(Long id);

    Model create(Model model);

    void delete(Long id);

    Model update(Model model);
}
