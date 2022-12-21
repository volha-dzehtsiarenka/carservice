package com.degtyarenko.mappers;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "brandId", target = "brand.id")
    Model toModel(ModelDto modelDto);

    ModelDto toModelDto(Model model);

}
