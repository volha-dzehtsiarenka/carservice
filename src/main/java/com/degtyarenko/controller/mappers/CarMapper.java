package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.CarDto;
import com.degtyarenko.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toCar(CarDto carDto);
}
