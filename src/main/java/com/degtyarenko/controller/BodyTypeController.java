package com.degtyarenko.controller;

import com.degtyarenko.controller.dto.BodyTypeDto;
import com.degtyarenko.controller.mappers.BodyTypeMapper;
import com.degtyarenko.entity.BodyType;
import com.degtyarenko.service.BodyTypeService;
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
@RequestMapping("/bodytype")
public class BodyTypeController {

    private final BodyTypeService service;
    private final BodyTypeMapper mapper;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(
                service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> findByName(@PathVariable String name) {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("deleted successful!", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createBodyType(@Valid @RequestBody BodyTypeDto dto) {
        BodyType bodyType = mapper.toBodyType(dto);
        return new ResponseEntity<>(service.create(bodyType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBodyType(@Valid @PathVariable Long id, @RequestBody BodyTypeDto dto) {
        BodyType result = service.findById(id);
        dto.setId(result.getId());
        BodyType bodyType = mapper.toBodyType(dto);
        return new ResponseEntity<>(service.update(bodyType), HttpStatus.OK);
    }

}
