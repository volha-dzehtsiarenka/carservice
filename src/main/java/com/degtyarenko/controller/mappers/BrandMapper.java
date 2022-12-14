package com.degtyarenko.controller.mappers;

import com.degtyarenko.controller.dto.BrandDto;
import com.degtyarenko.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toBrandDto(Brand brand);
    Brand toBrand(BrandDto brandDto);
}
