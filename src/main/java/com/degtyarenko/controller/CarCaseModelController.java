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
import java.util.List;

import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_CREATE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_DELETE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_FOUND;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_NOT_CREATED_CONFLICT;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_NOT_CREATED_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_NOT_DELETED_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_NOT_FOUND;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_NOT_UPDATE_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.CarCaseModelConstant.CAR_CASE_MODEL_UPDATE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarCaseModelConstant.CREATE_NEW_CAR_CASE_MODEL;
import static com.degtyarenko.constant.CarCaseModelConstant.DELETE_CAR_CASE_MODEL;
import static com.degtyarenko.constant.CarCaseModelConstant.FIND_ALL_CAR_CASE_MODEL;
import static com.degtyarenko.constant.CarCaseModelConstant.FIND_CAR_CASE_MODEL_BY_ID;
import static com.degtyarenko.constant.CarCaseModelConstant.UPDATE_CAR_CASE_MODEL;
import static com.degtyarenko.constant.StatusConstant.BAD_REQUEST;
import static com.degtyarenko.constant.StatusConstant.DELETED_SUCCESSFUL;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_200;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_201;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_400;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_404;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_500;
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
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_MODEL_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarCaseModelDto>> findAll() {
        List<CarCaseModel> all = carCaseModelService.findAll();
        return new ResponseEntity<>(carCaseModelMapper.toCarCaseModelDtoList(all), HttpStatus.OK);
    }


    @Operation(summary = FIND_CAR_CASE_MODEL_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_MODEL_FOUND,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_MODEL_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarCaseModelDto> findById(@PathVariable Long id) {
        CarCaseModel carCaseModelById = carCaseModelService.findById(id);
        return new ResponseEntity<>(carCaseModelMapper.toCarCaseModelDto(carCaseModelById), HttpStatus.OK);
    }

    @Operation(summary = DELETE_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_MODEL_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_MODEL_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        carCaseModelService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = CAR_CASE_MODEL_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseModelSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_MODEL_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarCaseModelDto> createCarCaseModel(@Valid @RequestBody CarCaseModelSaveDto carCaseModelDto) {
        CarCaseModel carCaseModelCreate = carCaseModelService.create(carCaseModelDto);
        return new ResponseEntity<>(carCaseModelMapper.toCarCaseModelDto(carCaseModelCreate), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_CAR_CASE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_MODEL_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_MODEL_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarCaseModelDto> updateCarCaseModel(@Valid @RequestBody CarCaseModelDto carCaseModelDto) {
        CarCaseModel carCaseModelUpdate = carCaseModelService.update(carCaseModelDto);
        return new ResponseEntity<>(carCaseModelMapper.toCarCaseModelDto(carCaseModelUpdate), HttpStatus.OK);
    }

}
