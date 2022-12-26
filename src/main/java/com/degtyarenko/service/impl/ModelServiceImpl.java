package com.degtyarenko.service.impl;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.entity.Model;
import com.degtyarenko.exeption.NotFoundException;
import com.degtyarenko.mappers.ModelMapper;
import com.degtyarenko.repository.ModelRepository;
import com.degtyarenko.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private static final String MODEL_NOT_FOUND = "Model not found";
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public Model findById(Long id) {
        return modelRepository.findById(id).orElseThrow(() ->
                new NotFoundException(id));
    }

    @Override
    @Transactional
    public Model create(ModelDto modelDto) {
        Model model = modelMapper.toModel(modelDto);
        return modelRepository.save(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (modelRepository.findById(id).isPresent()) {
            modelRepository.deleteById(id);
        } else throw new NotFoundException(id);
    }

    @Override
    @Transactional
    public Model update(ModelDto modelDto) {
        if (modelRepository.findById(modelDto.getId()).isPresent()) {
            return create(modelDto);
        } else throw new NotFoundException(modelDto.getId());
    }

}
