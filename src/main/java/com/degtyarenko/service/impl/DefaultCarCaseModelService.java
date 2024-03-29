package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.entity.CarCaseModel;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.CarCaseModelMapper;
import com.degtyarenko.repository.CarCaseModelRepository;
import com.degtyarenko.service.CarCaseModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_ALREADY_EXIST;
import static com.degtyarenko.constant.SchemaConstant.STRING;

/**
 * The type Car case model service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class DefaultCarCaseModelService implements CarCaseModelService {

    private final CarCaseModelRepository carCaseModelRepository;
    private final CarCaseModelMapper carCaseModelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CarCaseModel> findAll() {
        return carCaseModelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CarCaseModel findById(Long id) {
        return carCaseModelRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public CarCaseModel create(@Valid CarCaseModelDto carCaseModelDto) {
        CarCaseModel carCaseModel = carCaseModelRepository.findByCarCaseIdAndModelId(
                (carCaseModelDto.getCarCaseId()), carCaseModelDto.getModelId());
        if (Objects.nonNull(carCaseModel)) {
            throw new EntityIsUsedException(String.join(CAR_CASE_MODEL_ALREADY_EXIST, STRING, carCaseModel.toString()));
        }
        CarCaseModel newCarCaseModel = carCaseModelMapper.toCarCaseModel(carCaseModelDto);
        return carCaseModelRepository.save(newCarCaseModel);
    }

    @Override
    public void delete(Long id) {
        Optional<CarCaseModel> carCaseModelOptional = carCaseModelRepository.findById(id);
        if (carCaseModelOptional.isPresent()) {
            carCaseModelRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Override
    public CarCaseModel update(@Valid CarCaseModelDto carCaseModelDto) {
        CarCaseModel existingCarCaseModel = carCaseModelRepository.findByCarCaseIdAndModelId(
                carCaseModelDto.getCarCaseId(), carCaseModelDto.getModelId());
        if (existingCarCaseModel != null && !existingCarCaseModel.getId().equals(carCaseModelDto.getId())) {
            throw new EntityIsUsedException(String.join(CAR_CASE_MODEL_ALREADY_EXIST, STRING, existingCarCaseModel.toString()));
        }
        Optional<CarCaseModel> carCaseModelOptional = carCaseModelRepository.findById(carCaseModelDto.getId());
        if (carCaseModelOptional.isPresent()) {
            CarCaseModel updatedCarCaseModel = carCaseModelMapper.toCarCaseModel(carCaseModelDto);
            return carCaseModelRepository.save(updatedCarCaseModel);
        }
        throw new EntityNotFoundException(carCaseModelDto.getId());
    }

}
