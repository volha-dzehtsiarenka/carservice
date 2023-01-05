package com.degtyarenko.mappers;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import org.mapstruct.Mapper;

/**
 * The interface Brand mapper.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toBrand(BrandSaveDto brandSaveDto);

    Brand toBrand(BrandDto brandDto);

    BrandDto toBrandDto(Brand brand);
}
