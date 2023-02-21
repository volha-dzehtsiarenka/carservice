package com.degtyarenko.service.impl;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.dto.CarSaveDto;
import com.degtyarenko.entity.Car;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.CarMapper;
import com.degtyarenko.repository.CarRepository;
import com.degtyarenko.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Car create(CarSaveDto carDto) {
        Car car = carRepository.findByVinCode(carDto.getVinCode());
        if (!Objects.isNull(car)) {
            throw new EntityIsUsedException(String.join(CAR_IS_ALREADY_EXIST, STRING, car.toString()));
        }
        Car newCar = carMapper.toCar(carDto);
        return carRepository.save(newCar);
    }

    @Override
    public void delete(Long id) {
        if (carRepository.findById(id).isPresent()) {
            carRepository.deleteById(id);
        } else throw new EntityNotFoundException(id);
    }

    @Override
    public Car update(CarDto carDto) {
        Car car = carRepository.findByVinCode(carDto.getVinCode());
        if (Objects.nonNull(car)) {
            throw new EntityIsUsedException(String.join(CAR_IS_ALREADY_EXIST, STRING, car.toString()));
        } else if (carRepository.findById(carDto.getId()).isPresent()) {
            Car newCar = carMapper.toCar(carDto);
            return carRepository.save(newCar);
        } else throw new EntityNotFoundException(carDto.getId());
    }

}
