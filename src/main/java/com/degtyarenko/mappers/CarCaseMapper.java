package com.degtyarenko.mappers;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.dto.CarCaseSaveDto;
import com.degtyarenko.entity.CarCase;
import org.mapstruct.Mapper;

/**
 * The interface Car case mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface CarCaseMapper {

    CarCase toCarCase(CarCaseDto carCaseDto);

    CarCase toCarCase(CarCaseSaveDto carCaseSaveDto);

    CarCaseDto toCarCaseDto(CarCase carCase);
}
