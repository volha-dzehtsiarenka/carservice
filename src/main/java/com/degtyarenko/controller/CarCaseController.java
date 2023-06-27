package com.degtyarenko.controller;

import com.degtyarenko.dto.CarCaseDto;
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

import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_CREATE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_DELETE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_FOUND;
import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_NOT_CREATED_CONFLICT;
import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_NOT_FOUND;
import static com.degtyarenko.constant.CarCaseConstant.CAR_CASE_UPDATE_SUCCESSFULLY;
import static com.degtyarenko.constant.CarCaseConstant.CREATE_NEW_CAR_CASE;
import static com.degtyarenko.constant.CarCaseConstant.DELETE_CAR_CASE;
import static com.degtyarenko.constant.CarCaseConstant.FINDS_ALL_CAR_CASE;
import static com.degtyarenko.constant.CarCaseConstant.FIND_ALL_CAR_CASE;
import static com.degtyarenko.constant.CarCaseConstant.FIND_CAR_CASE_BY_ID;
import static com.degtyarenko.constant.CarCaseConstant.UPDATE_CAR_CASE;
import static com.degtyarenko.constant.StatusConstant.DELETED_SUCCESSFUL;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_200;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_201;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_404;
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
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class)))})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarCaseDto> findAll() {
        return carCaseMapper.toCarCaseDtoList(carCaseService.findAll());
    }

    @Operation(summary = FIND_CAR_CASE_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_FOUND,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_FOUND,
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
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        carCaseService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_CAR_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = CAR_CASE_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_CREATED_CONFLICT,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CarCaseDto createBodyType(@RequestBody CarCaseDto carCaseDto) {
        CarCase carCasePost = carCaseService.create(carCaseDto);
        return carCaseMapper.toCarCaseDto(carCasePost);
    }

    @Operation(summary = UPDATE_CAR_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = CAR_CASE_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = CarCaseDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = CAR_CASE_NOT_FOUND,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarCaseDto updateBodyType(@RequestBody CarCaseDto carCaseDto) {
        CarCase updateCarCase = carCaseService.update(carCaseDto);
        return carCaseMapper.toCarCaseDto(updateCarCase);
    }

}
