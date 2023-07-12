package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.CarMapper;
import com.degtyarenko.repository.CarRepository;
import com.degtyarenko.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.degtyarenko.constant.CarConstant.CAR_IS_ALREADY_EXIST;
import static com.degtyarenko.constant.SchemaConstant.STRING;

/**
 * The type Car service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class DefaultCarService implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Car> findAll(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return carRepository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Car create(@Valid CarDto carDto) {
        Car car = carRepository.findByVinCode(carDto.getVinCode());
        if (Objects.nonNull(car)) {
            throw new EntityIsUsedException(String.join(CAR_IS_ALREADY_EXIST, STRING, car.toString()));
        }
        Car newCar = carMapper.toCar(carDto);
        return carRepository.save(newCar);
    }

    @Override
    public void delete(Long id) {
        if (carRepository.findById(id).isPresent()) {
            carRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public Car update(@Valid CarDto carDto) {
        Car car = carRepository.findByVinCode(carDto.getVinCode());
        if (Objects.nonNull(car)) {
            throw new EntityIsUsedException(String.join(CAR_IS_ALREADY_EXIST, STRING, car.toString()));
        }
        Optional<Car> carById = carRepository.findById(carDto.getId());
        if (carById.isPresent()) {
            Car newCar = carMapper.toCar(carDto);
            return carRepository.save(newCar);
        }
        throw new EntityNotFoundException(carDto.getId());
    }

}
