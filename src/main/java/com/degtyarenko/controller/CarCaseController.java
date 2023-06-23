package com.degtyarenko.controller;

import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.dto.CarCaseSaveDto;
import com.degtyarenko.entity.CarCase;
import com.degtyarenko.mappers.CarCaseMapper;
import com.degtyarenko.service.CarCaseService;
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

import static com.degtyarenko.constant.CarCaseConstant.*;
import static com.degtyarenko.constant.StatusConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Car case controller.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/carcase")
@Tag(name = "Car case controller")
public class CarCaseController {

    private final CarCaseService carCaseService;
    private final CarCaseMapper carCaseMapper;

    @Operation(summary = FIND_ALL_CAR_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FINDS_ALL_CAR_CASE,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarCaseDto> findAll() {
        return carCaseMapper.toCarCaseDtoList(carCaseService.findAll());
    }

    @Operation(summary = FIND_CAR_CASE_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_FOUND,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarCaseDto findById(@PathVariable Long id) {
        CarCase carCaseById = carCaseService.findById(id);
        return carCaseMapper.toCarCaseDto(carCaseById);
    }

    @Operation(summary = DELETE_CAR_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        carCaseService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_CAR_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = CAR_CASE_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CarCaseDto createBodyType(@Valid @RequestBody CarCaseSaveDto carCaseDto) {
        CarCase carCasePost = carCaseService.create(carCaseDto);
        return carCaseMapper.toCarCaseDto(carCasePost);
    }

    @Operation(summary = UPDATE_CAR_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = CAR_CASE_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarCaseDto updateBodyType(@Valid @RequestBody CarCaseDto carCaseDto) {
        CarCase updateCarCase = carCaseService.update(carCaseDto);
        return carCaseMapper.toCarCaseDto(updateCarCase);
    }

}
