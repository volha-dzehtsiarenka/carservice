package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.dto.CarCaseModelSaveDto;
import com.degtyarenko.entity.CarCaseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface Car case model mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface CarCaseModelMapper {

    @Mapping(source = "carCaseId", target = "carCase.id")
    @Mapping(source = "modelId", target = "model.id")
    CarCaseModel toCarCaseModel(CarCaseModelDto carCaseModelDto);

    @Mapping(source = "carCaseId", target = "carCase.id")
    @Mapping(source = "modelId", target = "model.id")
    CarCaseModel toCarCaseModel(CarCaseModelSaveDto carCaseModelSaveDto);

}
