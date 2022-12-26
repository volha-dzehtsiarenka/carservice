package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.entity.CarCase;
import com.degtyarenko.exeption.NotFoundException;
import com.degtyarenko.mappers.CarCaseMapper;
import com.degtyarenko.repository.CarCaseRepository;
import com.degtyarenko.service.CarCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarCaseServiceImpl implements CarCaseService {

    private static final String CAR_CASE_NOT_FOUND = "Car case not found";
    private final CarCaseRepository carCaseRepository;
    private final CarCaseMapper carCaseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CarCase> findAll() {
        return carCaseRepository.findAll();
    }

    @Override
    public CarCase findById(Long id) {
        return carCaseRepository.findById(id).orElseThrow(() ->
                new NotFoundException(id));
    }

    @Override
    @Transactional
    public CarCase create(CarCaseDto carCaseDto) {
        CarCase carCase = carCaseMapper.toCarCase(carCaseDto);
        return carCaseRepository.save(carCase);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (carCaseRepository.findById(id).isPresent()) {
            carCaseRepository.deleteById(id);
        } else throw new NotFoundException(id);
    }

    @Override
    @Transactional
    public CarCase update(CarCaseDto carCaseDto) {
        if (carCaseRepository.findById(carCaseDto.getId()).isPresent()) {
            return create(carCaseDto);
        } else throw new NotFoundException(carCaseDto.getId());
    }

}
