package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.entity.CarCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The interface Car case mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface CarCaseMapper {

    @Mapping(target = "id", ignore = true)
    CarCase toCarCase(CarCaseDto carCaseDto);

    CarCaseDto toCarCaseDto(CarCase carCase);

    List<CarCaseDto> toCarCaseDtoList(List<CarCase> list);

}
