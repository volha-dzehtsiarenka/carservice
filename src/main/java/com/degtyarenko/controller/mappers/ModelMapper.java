package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.ModelDto;
import com.degtyarenko.entity.Model;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    ModelDto toModelDto(Model model);
    List<ModelDto> toModelDto(List<Model> models);
    Model toModel(ModelDto modelDto);
}
