package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.ModelDto;
import com.degtyarenko.entity.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    Model toModel(ModelDto modelDto);
}
