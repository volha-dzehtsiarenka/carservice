package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.dto.CarCaseSaveDto;
import com.degtyarenko.entity.CarCase;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.CarCaseMapper;
import com.degtyarenko.repository.CarCaseRepository;
import com.degtyarenko.service.CarCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_ALREADY_EXIST;
import static com.degtyarenko.constant.SchemaConstant.STRING;

/**
 * The type Car case service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CarCaseServiceImpl implements CarCaseService {

    private final CarCaseRepository carCaseRepository;
    private final CarCaseMapper carCaseMapper;

    @Override
    public List<CarCase> findAll() {
        return carCaseRepository.findAll();
    }

    @Override
    public CarCase findById(Long id) {
        return carCaseRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public CarCase create(CarCaseSaveDto carCaseDto) {
        CarCase carCase = carCaseRepository.findByName(carCaseDto.getName());
        if (!Objects.isNull(carCase)) {
            throw new EntityIsUsedException(String.join(CAR_CASE_ALREADY_EXIST, STRING, carCase.toString()));
        }
        CarCase newCarCase = carCaseMapper.toCarCase(carCaseDto);
        return carCaseRepository.save(newCarCase);
    }

    @Override
    public void delete(Long id) {
        if (carCaseRepository.findById(id).isPresent()) {
            carCaseRepository.deleteById(id);
        } else throw new EntityNotFoundException(id);
    }

    @Override
    public CarCase update(CarCaseDto carCaseDto) {
        CarCase carCase = carCaseRepository.findByName(carCaseDto.getName());
        Optional<CarCase> carCaseById = carCaseRepository.findById(carCaseDto.getId());
        if (Objects.nonNull(carCase)) {
            throw new EntityIsUsedException(String.join(CAR_CASE_ALREADY_EXIST, STRING, carCase.toString()));
        }
        if (carCaseById.isPresent()) {
            CarCase newCarCase = carCaseMapper.toCarCase(carCaseDto);
            return carCaseRepository.save(newCarCase);
        } else throw new EntityNotFoundException(carCaseDto.getId());
    }

}
