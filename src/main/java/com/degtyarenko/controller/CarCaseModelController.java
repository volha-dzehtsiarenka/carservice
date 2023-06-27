package com.degtyarenko.controller;

import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.dto.CarCaseModelSaveDto;
import com.degtyarenko.entity.CarCaseModel;
import com.degtyarenko.mappers.CarCaseModelMapper;
import com.degtyarenko.service.CarCaseModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.degtyarenko.constant.CarCaseModelConstant.*;
import static com.degtyarenko.constant.StatusConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Car case model controller.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/car_case_model")
@Tag(name = "Car case model controller")
public class CarCaseModelController {

    private final CarCaseModelService carCaseModelService;
    private final CarCaseModelMapper carCaseModelMapper;

    @Operation(summary = FIND_ALL_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_CAR_CASE_MODEL,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class)))})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarCaseModelDto> findAll() {
        List<CarCaseModel> all = carCaseModelService.findAll();
        return carCaseModelMapper.toCarCaseModelDtoList(all);
    }


    @Operation(summary = FIND_CAR_CASE_MODEL_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_MODEL_FOUND,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_FOUND,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarCaseModelDto findById(@PathVariable Long id) {
        CarCaseModel carCaseModelById = carCaseModelService.findById(id);
        return carCaseModelMapper.toCarCaseModelDto(carCaseModelById);
    }

    @Operation(summary = DELETE_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_MODEL_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_FOUND,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        carCaseModelService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = CAR_CASE_MODEL_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseModelSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CarCaseModelDto createCarCaseModel(@RequestBody CarCaseModelSaveDto carCaseModelDto) {
        CarCaseModel carCaseModelCreate = carCaseModelService.create(carCaseModelDto);
        return carCaseModelMapper.toCarCaseModelDto(carCaseModelCreate);
    }

    @Operation(summary = UPDATE_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_MODEL_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_FOUND,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarCaseModelDto updateCarCaseModel(@RequestBody CarCaseModelDto carCaseModelDto) {
        CarCaseModel carCaseModelUpdate = carCaseModelService.update(carCaseModelDto);
        return carCaseModelMapper.toCarCaseModelDto(carCaseModelUpdate);
    }

}
