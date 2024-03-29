package com.degtyarenko.mappers;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

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
    @Mapping(target = "id", ignore = true)
    Model toModel(ModelDto modelDto);

    @Mapping(source = "brand.id", target = "brandId")
    ModelDto toModelDto(Model model);

    List<ModelDto> toModelDtoList(List<Model> modelList);

}
