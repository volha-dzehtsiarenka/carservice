package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.BodyTypeDto;
import com.degtyarenko.entity.BodyType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BodyTypeMapper {

    BodyTypeDto toBodyTypeDto(BodyType bodyType);
    List<BodyTypeDto> toBodyTypeDto(List<BodyType> bodyTypes);
    BodyType toBodyType(BodyTypeDto bodyTypeDto);
}
