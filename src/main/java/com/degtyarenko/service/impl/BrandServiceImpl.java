package com.degtyarenko.service.impl;

import com.degtyarenko.entity.Brand;
import com.degtyarenko.repository.BrandRepository;
import com.degtyarenko.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return repository.findById(id).orElse(new Brand());
    }

    @Override
    @Transactional
    public Brand create(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Brand update(Brand brand) {
        return repository.save(brand);
    }

}
