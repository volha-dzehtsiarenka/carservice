package com.degtyarenko.service.impl;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.repository.BrandRepository;
import com.degtyarenko.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
@Validated
@Transactional
@RequiredArgsConstructor
public class DefaultBrandService implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Brand> findAll(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return brandRepository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(id));
    }

    @Override
    public Brand create(@Valid BrandDto brandDto) {
        Brand brand = brandRepository.findByBrandName(brandDto.getBrandName());
        if (Objects.nonNull(brand)) {
            throw new EntityIsUsedException(String.join(BRAND_ALREADY_EXIST, STRING, brand.toString()));
        }
        Brand newBrand = brandMapper.toBrand(brandDto);
        return brandRepository.save(newBrand);
    }

    @Override
    public void delete(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            brandRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Override
    public Brand update(@Valid BrandDto brandDto) throws EntityNotFoundException {
        Optional<Brand> brandOptional = brandRepository.findById(brandDto.getId());
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            Brand existingBrand = brandRepository.findByBrandName(brandDto.getBrandName());
            if (Objects.nonNull(existingBrand) && !existingBrand.equals(brand)) {
                throw new EntityIsUsedException(String.join(BRAND_ALREADY_EXIST, STRING, existingBrand.toString()));
            }
            brand.setBrandName(brandDto.getBrandName());
            return brandRepository.save(brand);
        } else throw new EntityNotFoundException(brandDto.getId());
    }

}
