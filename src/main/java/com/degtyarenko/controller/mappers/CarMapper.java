package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.CarDto;
import com.degtyarenko.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toCarDto(Car car);
    List<CarDto> toCarDto(List<Car> cars);
    Car toCar(CarDto carDto);
}
