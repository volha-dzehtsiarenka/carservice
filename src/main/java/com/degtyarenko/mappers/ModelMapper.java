package com.degtyarenko.mappers;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.dto.ModelSaveDto;
import com.degtyarenko.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface Model mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "brandId", target = "brand.id")
    Model toModel(ModelDto modelDto);

    @Mapping(source = "brandId", target = "brand.id")
    Model toModel(ModelSaveDto modelSaveDto);

    ModelDto toModelDto(Model model);

}
