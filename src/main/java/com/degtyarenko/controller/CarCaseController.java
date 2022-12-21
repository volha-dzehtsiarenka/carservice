package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.CarCaseDto;
import com.degtyarenko.service.CarCaseService;
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
@RequestMapping("/carcase")
public class CarCaseController {

    private final CarCaseService carCaseService;


    @Operation(summary = "Find all car case", responses = {
            @ApiResponse(responseCode = "200", description = "Finds all car case",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "500", description = "Car case not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(
                carCaseService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find car case by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Car case found",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Car case not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carCaseService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Car case", responses = {
            @ApiResponse(responseCode = "200", description = "Car case delete successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Car case not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case not deleted, Illegal Arguments",
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        carCaseService.delete(id);
        return new ResponseEntity<>("Deleted successful !", HttpStatus.OK);
    }

    @Operation(summary = "Create new Car case", responses = {
            @ApiResponse(responseCode = "201", description = "Car case create successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "409", description = "Car case not created, Conflict",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car case not created, Illegal Arguments",
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createBodyType(@Valid @RequestBody CarCaseDto carCaseDto) {
        carCaseService.create(carCaseDto);
        return new ResponseEntity<>("Car case create successfully !", HttpStatus.CREATED);
    }

    @Operation(summary = "Update car case", responses = {
            @ApiResponse(responseCode = "200", description = "Car case update successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Car case not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "car case not update, Illegal Arguments",
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBodyType(@Valid @RequestBody CarCaseDto carCaseDto) {
        carCaseService.update(carCaseDto);
        return new ResponseEntity<>("Car case update successfully !", HttpStatus.OK);
    }

}
