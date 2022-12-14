package com.degtyarenko.controller;

import com.degtyarenko.controller.dto.CarDto;
import com.degtyarenko.controller.mappers.CarMapper;
import com.degtyarenko.entity.Car;
import com.degtyarenko.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService service;

    private final CarMapper mapper;

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
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDto dto) {
        Car car = mapper.toCar(dto);
        return new ResponseEntity<>(service.create(car),HttpStatus.CREATED);
    }

}
