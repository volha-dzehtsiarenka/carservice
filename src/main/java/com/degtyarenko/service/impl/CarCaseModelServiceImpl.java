package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.entity.CarCaseModel;
import com.degtyarenko.exeption.NotFoundException;
import com.degtyarenko.mappers.CarCaseModelMapper;
import com.degtyarenko.repository.CarCaseModelRepository;
import com.degtyarenko.service.CarCaseModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarCaseModelServiceImpl implements CarCaseModelService {

    private static final String CAR_CASE_MODEL_NOT_FOUND = "Car case model not found";
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
                new NotFoundException(CAR_CASE_MODEL_NOT_FOUND));
    }

    @Override
    public CarCaseModel create(CarCaseModelDto carCaseModelDto) {
        CarCaseModel carCaseModel = carCaseModelMapper.toCarCaseModel(carCaseModelDto);
        return carCaseModelRepository.save(carCaseModel);
    }

    @Override
    public void delete(Long id) {
        if (carCaseModelRepository.findById(id).isPresent()) {
            carCaseModelRepository.deleteById(id);
        } else throw new NotFoundException(CAR_CASE_MODEL_NOT_FOUND);
    }

    @Override
    public CarCaseModel update(CarCaseModelDto carCaseModelDto) {
        if (carCaseModelRepository.findById(carCaseModelDto.getId()).isPresent()) {
            return create(carCaseModelDto);
        } else throw new NotFoundException(CAR_CASE_MODEL_NOT_FOUND);
    }

}
