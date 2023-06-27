package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

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
    @Mapping(target = "id", ignore = true)
    Car toCar(CarDto carDto);

    @Mapping(source = "carCaseModel.id", target = "carCaseModelId")
    CarDto toCarDto(Car car);

    List<CarDto> toCarDtoList(List<Car> carList);

}
