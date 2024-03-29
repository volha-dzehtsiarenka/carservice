package com.degtyarenko.service.impl;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.entity.Model;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.ModelMapper;
import com.degtyarenko.repository.ModelRepository;
import com.degtyarenko.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.degtyarenko.constant.ModelConstant.MODEL_ALREADY_EXIST;
import static com.degtyarenko.constant.SchemaConstant.STRING;

/**
 * The type Model service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class DefaultModelService implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Model findById(Long id) {
        return modelRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Model create(@Valid ModelDto modelDto) {
        Model model = modelRepository.findByModelName(modelDto.getModelName());
        if (Objects.nonNull(model)) {
            throw new EntityIsUsedException(String.join(MODEL_ALREADY_EXIST, STRING, model.toString()));
        }
        Model newModel = modelMapper.toModel(modelDto);
        return modelRepository.save(newModel);
    }

    @Override
    public void delete(Long id) {
        Optional<Model> modelById = modelRepository.findById(id);
        if (modelById.isPresent()) {
            modelRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Override
    public Model update(@Valid ModelDto modelDto) {
        Model model = modelRepository.findByModelName(modelDto.getModelName());
        if (Objects.nonNull(model)) {
            throw new EntityIsUsedException(String.join(MODEL_ALREADY_EXIST, STRING, model.toString()));
        }
        Optional<Model> modelById = modelRepository.findById(modelDto.getId());
        if (modelById.isPresent()) {
            Model newModel = modelMapper.toModel(modelDto);
            return modelRepository.save(newModel);
        }
        throw new EntityNotFoundException(modelDto.getId());
    }

}
