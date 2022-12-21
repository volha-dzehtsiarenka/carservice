package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.service.BrandService;
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
@RequestMapping("/brand")
public class BrandController {

    private final BrandService service;

    @Operation(summary = "Find all brands", responses = {
            @ApiResponse(responseCode = "200", description = "Find all brands",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "500", description = "Brands not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find brand by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Brand found",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Brand not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Brand", responses = {
            @ApiResponse(responseCode = "200", description = "Brand delete successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Brand not deleted, Illegal Arguments",
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Deleted successful !", HttpStatus.OK);
    }

    @Operation(summary = "Create new Brand", responses = {
            @ApiResponse(responseCode = "201", description = "Brand create successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "409", description = "Brand not created, Conflict",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Brand not created, Illegal Arguments",
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createBrand(@Valid @RequestBody BrandDto brandDto) {
        return new ResponseEntity<>(service.create(brandDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Brand", responses = {
            @ApiResponse(responseCode = "200", description = "Brand update successfully !",
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Brand not update, Illegal Arguments",
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBrand(@Valid @RequestBody BrandDto brandDto) {
        service.update(brandDto);
        return new ResponseEntity<>("Brand update successfully !", HttpStatus.OK);
    }

}
