package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.dto.CarCaseModelSaveDto;
import com.degtyarenko.entity.CarCaseModel;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.CarCaseModelMapper;
import com.degtyarenko.repository.CarCaseModelRepository;
import com.degtyarenko.service.CarCaseModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
@RequiredArgsConstructor
public class CarCaseModelServiceImpl implements CarCaseModelService {

    private final CarCaseModelRepository carCaseModelRepository;
    private final CarCaseModelMapper carCaseModelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CarCaseModel> findAll() {
        return carCaseModelRepository.findAll();
    }

    @Override
    public CarCaseModel findById(Long id) {
        return carCaseModelRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public CarCaseModel create(CarCaseModelSaveDto carCaseModelDto) {
        CarCaseModel carCaseModel = carCaseModelRepository.findByCarCaseIdAndModelId(
                (carCaseModelDto.getCarCaseId()), carCaseModelDto.getModelId());
        if (!Objects.isNull(carCaseModel)) {
            throw new EntityIsUsedException(String.join(CAR_CASE_MODEL_ALREADY_EXIST, STRING, carCaseModel.toString()));
        }
        CarCaseModel newCarCaseModel = carCaseModelMapper.toCarCaseModel(carCaseModelDto);
        return carCaseModelRepository.save(newCarCaseModel);
    }

    @Override
    public void delete(Long id) {
        if (carCaseModelRepository.findById(id).isPresent()) {
            carCaseModelRepository.deleteById(id);
        } else throw new EntityNotFoundException(id);
    }

    @Override
    public CarCaseModel update(CarCaseModelDto carCaseModelDto) {
        CarCaseModel carCaseModel = carCaseModelRepository.findByCarCaseIdAndModelId(
                (carCaseModelDto.getCarCaseId()), carCaseModelDto.getModelId());
        if (!Objects.isNull(carCaseModel)) {
            throw new EntityIsUsedException(String.join(CAR_CASE_MODEL_ALREADY_EXIST, STRING, carCaseModel.toString()));
        } else if (carCaseModelRepository.findById(carCaseModelDto.getId()).isPresent()) {
            CarCaseModel newCarCaseModel = carCaseModelMapper.toCarCaseModel(carCaseModelDto);
            return carCaseModelRepository.save(newCarCaseModel);
        } else throw new EntityNotFoundException(carCaseModelDto.getId());
    }

}
