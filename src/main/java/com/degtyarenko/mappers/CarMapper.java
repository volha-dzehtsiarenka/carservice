package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "carCaseModelId", target = "carCaseModel.id")
    Car toCar(CarDto carDto);

    CarDto toCarDto(Car car);
}
