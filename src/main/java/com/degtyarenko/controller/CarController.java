package com.degtyarenko.controller;

import com.degtyarenko.dto.AbstractDto;
import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.CarDto;
import com.degtyarenko.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService service;

    @Operation(summary = "Find all cars", responses = {
            @ApiResponse(responseCode = "200", description = "Find all cars",
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = "500", description = "Cars not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find car by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Car found",
                    content = @Content(schema = @Schema(implementation = AbstractDto.class))),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Car", responses = {
            @ApiResponse(responseCode = "200", description = "Car delete successfully !",
                    content = @Content(schema = @Schema(implementation = AbstractDto.class))),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not deleted, Illegal Arguments",
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Deleted successful !", HttpStatus.OK);
    }

    @Operation(summary = "Create new Car", responses = {
            @ApiResponse(responseCode = "201", description = "Car create successfully !",
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = "409", description = "Car not created, Conflict",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not created, Illegal Arguments",
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDto carDto) {
        return new ResponseEntity<>(service.create(carDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update car", responses = {
            @ApiResponse(responseCode = "200", description = "Car update successfully !",
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not update, Illegal Arguments",
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCar(@Valid @RequestBody CarDto carDto) {
        return new ResponseEntity<>(service.update(carDto), HttpStatus.OK);
    }

}
