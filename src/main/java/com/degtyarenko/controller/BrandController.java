package com.degtyarenko.controller;

import com.degtyarenko.controller.dto.BrandDto;
import com.degtyarenko.controller.mappers.BrandMapper;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final BrandService service;
    private final BrandMapper mapper;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("deleted successful!", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createBrand(@Valid @RequestBody BrandDto dto) {
        Brand brand = mapper.toBrand(dto);
        return new ResponseEntity<>(service.create(brand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBrand(@Valid @PathVariable Long id, @RequestBody BrandDto dto) {
        Brand result = service.findById(id);
        dto.setId(result.getId());
        Brand brand = mapper.toBrand(dto);
        return new ResponseEntity<>(service.update(brand), HttpStatus.OK);
    }

}
