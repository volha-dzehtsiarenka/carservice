package com.degtyarenko.service.impl;

import com.degtyarenko.entity.Model;
import com.degtyarenko.repository.ModelRepository;
import com.degtyarenko.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository repository;

    @Override
    public List<Model> findAll() {
        return repository.findAll();
    }

    @Override
    public Model findById(Long id) {
        return repository.findById(id).orElse(new Model());
    }

    @Override
    @Transactional
    public Model create(Model model) {
        return repository.save(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Model update(Model model) {
        return repository.save(model);
    }

}
