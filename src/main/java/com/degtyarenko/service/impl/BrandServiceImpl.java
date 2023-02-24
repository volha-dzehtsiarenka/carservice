package com.degtyarenko.service.impl;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.repository.BrandRepository;
import com.degtyarenko.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.degtyarenko.constant.BrandConstant.BRAND_ALREADY_EXIST;
import static com.degtyarenko.constant.SchemaConstant.STRING;

/**
 * The type Brand service.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Brand create(BrandSaveDto brandDto) {
        Brand brand = brandRepository.findByBrandName(brandDto.getBrandName());
        if (Objects.nonNull(brand)) {
            throw new EntityIsUsedException(String.join(BRAND_ALREADY_EXIST, STRING, brand.toString()));
        }
        Brand newBrand = brandMapper.toBrand(brandDto);
        return brandRepository.save(newBrand);
    }

    @Override
    public void delete(Long id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public Brand update(BrandDto brandDto) {
        Brand brand = brandRepository.findByBrandName(brandDto.getBrandName());
        if (Objects.nonNull(brand)) {
            throw new EntityIsUsedException(String.join(BRAND_ALREADY_EXIST, STRING, brand.toString()));
        }
        Optional<Brand> brandById = brandRepository.findById(brandDto.getId());
        if (brandById.isPresent()) {
            Brand newBrand = brandMapper.toBrand(brandDto);
            return brandRepository.save(newBrand);
        }
        throw new EntityNotFoundException(brandDto.getId());
    }

}
