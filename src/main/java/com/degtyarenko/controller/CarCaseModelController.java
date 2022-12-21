package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.CarCaseModelDto;
import com.degtyarenko.service.CarCaseModelService;
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
@RequestMapping("/carcasemodel")
public class CarCaseModelController {

    private final CarCaseModelService carCaseModelService;

    @Operation(summary = "Find all car case-model", responses = {
            @ApiResponse(responseCode = "200", description = "Finds all car case-model",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "500", description = "Car case-model not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(
                carCaseModelService.findAll(), HttpStatus.OK);
    }


    @Operation(summary = "Find car case - model by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Car case - model found",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Car case - model not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case - model not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carCaseModelService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Car case - model", responses = {
            @ApiResponse(responseCode = "200", description = "Car case - model delete successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Car case - model not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case - model not deleted, Illegal Arguments",
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        carCaseModelService.delete(id);
        return new ResponseEntity<>("Deleted successful !", HttpStatus.OK);
    }

    @Operation(summary = "Create new Car case - model", responses = {
            @ApiResponse(responseCode = "201", description = "Car case - model create successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "409", description = "Car case - model not created, Conflict",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case - model not created, Illegal Arguments",
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCarCaseModel(@Valid @RequestBody CarCaseModelDto carCaseModelDto) {
        carCaseModelService.create(carCaseModelDto);
        return new ResponseEntity<>("Car case - model create successfully !", HttpStatus.CREATED);
    }

    @Operation(summary = "Update car case - model", responses = {
            @ApiResponse(responseCode = "200", description = "Car case - model update successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Car case - model not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case - model not update, Illegal Arguments",
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCarCaseModel(@Valid @RequestBody CarCaseModelDto carCaseModelDto) {
        carCaseModelService.update(carCaseModelDto);
        return new ResponseEntity<>("Car case - model update successfully !", HttpStatus.OK);
    }

}
