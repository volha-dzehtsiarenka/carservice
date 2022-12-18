package com.degtyarenko.service.impl;

import com.degtyarenko.entity.BodyType;
import com.degtyarenko.exeption.NotFoundException;
import com.degtyarenko.repository.BodyTypeRepository;
import com.degtyarenko.service.BodyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyTypeServiceImpl implements BodyTypeService {

    private final BodyTypeRepository repository;

    @Override
    public List<BodyType> findAll() {
        return repository.findAll();
    }

    @Override
    public BodyType findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Body type not found"));
    }

    @Override
    @Transactional
    public BodyType create(BodyType bodyType) {
        return repository.save(bodyType);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else throw new NotFoundException("Body type not found");
    }

    @Override
    @Transactional
    public BodyType update(BodyType bodyType) {
        return create(bodyType);
    }

}
