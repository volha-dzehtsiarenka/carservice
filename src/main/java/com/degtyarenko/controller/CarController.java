package com.degtyarenko.controller;

import com.degtyarenko.dto.CarDto;
import com.degtyarenko.entity.Car;
import com.degtyarenko.mappers.CarMapper;
import com.degtyarenko.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.degtyarenko.constant.CarConstant.CAR_CREATE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarConstant.CAR_DELETE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarConstant.CAR_FOUND;
import static com.degtyarenko.constant.CarConstant.CAR_NOT_CREATED_CONFLICT;
import static com.degtyarenko.constant.CarConstant.CAR_NOT_FOUND;
import static com.degtyarenko.constant.CarConstant.CAR_UPDATE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarConstant.CREATE_NEW_CAR;
import static com.degtyarenko.constant.CarConstant.DELETE_CAR;
import static com.degtyarenko.constant.CarConstant.FIND_ALL_CARS;
import static com.degtyarenko.constant.CarConstant.FIND_CAR_BY_ID;
import static com.degtyarenko.constant.CarConstant.UPDATE_CAR;
import static com.degtyarenko.constant.StatusConstant.DELETED_SUCCESSFUL;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_200;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_201;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_404;
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
    private final CarMapper carMapper;

    @Operation(summary = FIND_ALL_CARS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_CARS,
                    content = @Content(schema = @Schema(implementation = CarDto.class)))})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> findAll() {
        return carMapper.toCarDtoList(carService.findAll());
    }

    @Operation(summary = FIND_CAR_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_FOUND,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_FOUND,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarDto findById(@PathVariable Long id) {
        Car carById = carService.findById(id);
        return carMapper.toCarDto(carById);
    }

    @Operation(summary = DELETE_CAR, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_FOUND,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        carService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_CAR, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = CAR_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_CREATED_CONFLICT,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CarDto carDto) {
        Car carCreate = carService.create(carDto);
        return carMapper.toCarDto(carCreate);
    }

    @Operation(summary = UPDATE_CAR, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_NOT_FOUND,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarDto updateCar(@RequestBody CarDto carDto) {
        Car carUpdate = carService.update(carDto);
        return carMapper.toCarDto(carUpdate);
    }

}
