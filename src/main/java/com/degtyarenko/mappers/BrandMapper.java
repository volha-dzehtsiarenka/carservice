package com.degtyarenko.mappers;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toBrand(BrandDto brandDto);

    BrandDto toBrandDto(Brand brand);
}
