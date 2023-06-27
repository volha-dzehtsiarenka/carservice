package com.degtyarenko.mappers;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The interface Brand mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    Brand toBrand(BrandDto brandDto);

    BrandDto toBrandDto(Brand brand);

    List<BrandDto> toBrandDtoList(List<Brand> list);

}
