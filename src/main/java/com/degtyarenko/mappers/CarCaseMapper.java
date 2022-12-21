package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.entity.CarCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarCaseMapper {

    CarCase toCarCase(CarCaseDto carCaseDto);

    CarCaseDto toCarCaseDto(CarCase carCase);
}
