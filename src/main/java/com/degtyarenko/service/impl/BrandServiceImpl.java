package com.degtyarenko.service.impl;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.exeption.NotFoundException;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.repository.BrandRepository;
import com.degtyarenko.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private static final String BRAND_NOT_FOUND = "Brand not found";
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new NotFoundException(BRAND_NOT_FOUND));
    }

    @Override
    @Transactional
    public Brand create(BrandDto brandDto) {
        Brand brand = brandMapper.toBrand(brandDto);
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        } else throw new NotFoundException(BRAND_NOT_FOUND);
    }

    @Override
    @Transactional
    public Brand update(BrandDto brandDto) {
        if (brandRepository.findById(brandDto.getId()).isPresent()) {
            return create(brandDto);
        } else throw new NotFoundException(BRAND_NOT_FOUND);
    }

}
