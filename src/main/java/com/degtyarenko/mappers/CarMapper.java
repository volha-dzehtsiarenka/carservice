package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.dto.CarSaveDto;
import com.degtyarenko.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface Car mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "carCaseModelId", target = "carCaseModel.id")
    Car toCar(CarDto carDto);

    @Mapping(source = "carCaseModelId", target = "carCaseModel.id")
    Car toCar(CarSaveDto carSaveDto);

    CarDto toCarDto(Car car);
}
