package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;
import com.degtyarenko.exeption.NotFoundException;
import com.degtyarenko.mappers.CarMapper;
import com.degtyarenko.repository.CarRepository;
import com.degtyarenko.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private static final String CAR_NOT_FOUND = "Car not found";
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new NotFoundException(CAR_NOT_FOUND));
    }

    @Override
    @Transactional
    public Car create(CarDto carDto) {
        Car car = carMapper.toCar(carDto);
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (carRepository.findById(id).isPresent()) {
            carRepository.deleteById(id);
        } else throw new NotFoundException(CAR_NOT_FOUND);
    }

    @Override
    @Transactional
    public Car update(CarDto carDto) {
        if (carRepository.findById(carDto.getId()).isPresent()) {
            return create(carDto);
        } else throw new NotFoundException(CAR_NOT_FOUND);
    }

}
