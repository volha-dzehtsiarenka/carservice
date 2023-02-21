package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.service.BrandService;
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

import static com.degtyarenko.constant.BrandConstant.*;
import static com.degtyarenko.constant.StatusConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Brand controller.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
@Tag(name = "Brand controller")
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = FIND_ALL_BRANDS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_BRANDS,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRANDS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = FIND_BRAND_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_FOUND,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = DELETE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(DELETED_SUCCESSFUL, HttpStatus.OK);
    }

    @Operation(summary = CREATE_NEW_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = BRAND_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createBrand(@Valid @RequestBody BrandSaveDto brandDto) {
        return new ResponseEntity<>(brandService.create(brandDto), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBrand(@Valid @RequestBody BrandDto brandDto) {
        return new ResponseEntity<>(brandService.update(brandDto), HttpStatus.OK);
    }

}
