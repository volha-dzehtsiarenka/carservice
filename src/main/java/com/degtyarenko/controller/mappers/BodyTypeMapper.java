package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.BodyTypeDto;
import com.degtyarenko.entity.BodyType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BodyTypeMapper {

    BodyType toBodyType(BodyTypeDto bodyTypeDto);
}
