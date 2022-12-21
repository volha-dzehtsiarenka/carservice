package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.entity.CarCase;
import com.degtyarenko.entity.CarCaseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarCaseModelMapper {

    @Mapping(source = "carCaseId", target = "carCase.id")
    @Mapping(source = "modelId", target = "model.id")
    CarCaseModel toCarCaseModel(CarCaseModelDto carCaseModelDto);

    CarCaseModelDto toCarCaseModelDto(CarCase carCase);
}
