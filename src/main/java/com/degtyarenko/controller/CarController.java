package com.degtyarenko.controller;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.dto.CarSaveDto;
import com.degtyarenko.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static com.degtyarenko.Constant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Car controller.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@Tag(name = "Car controller")
public class CarController {

    private final CarService carService;

    @Operation(summary = FIND_ALL_CARS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_CARS,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CARS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = FIND_CAR_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_FOUND,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = DELETE_CAR, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        carService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_CAR, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = CAR_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarSaveDto carDto) {
        return new ResponseEntity<>(carService.create(carDto), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_CAR, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCar(@Valid @RequestBody CarDto carDto) {
        return new ResponseEntity<>(carService.update(carDto), HttpStatus.OK);
    }

}
