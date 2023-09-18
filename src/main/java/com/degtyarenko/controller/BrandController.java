package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
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

import static com.degtyarenko.constant.BrandConstant.BRAND_CREATE_SUCCESSFULLY;
import static com.degtyarenko.constant.BrandConstant.BRAND_DELETE_SUCCESSFULLY;
import static com.degtyarenko.constant.BrandConstant.BRAND_FOUND;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_CREATED_CONFLICT;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_FOUND;
import static com.degtyarenko.constant.BrandConstant.BRAND_UPDATE_SUCCESSFULLY;
import static com.degtyarenko.constant.BrandConstant.CREATE_NEW_BRAND;
import static com.degtyarenko.constant.BrandConstant.DELETE_BRAND;
import static com.degtyarenko.constant.BrandConstant.FIND_ALL_BRANDS;
import static com.degtyarenko.constant.BrandConstant.FIND_BRAND_BY_ID;
import static com.degtyarenko.constant.BrandConstant.UPDATE_BRAND;
import static com.degtyarenko.constant.StatusConstant.DELETED_SUCCESSFUL;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_200;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_201;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_404;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Brand controller.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
@Tag(name = "Brand controller")
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @Operation(summary = FIND_ALL_BRANDS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_BRANDS,
                    content = @Content(schema = @Schema(implementation = BrandDto.class)))})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<Brand> findAllBrand(Integer page, Integer size) {
        return brandService.findAll(page, size);
    }

    @Operation(summary = FIND_BRAND_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_FOUND,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BrandDto findById(@PathVariable Long id) {
        return brandMapper.toBrandDto(brandService.findById(id));
    }


    @Operation(summary = DELETE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        brandService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = BRAND_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_CREATED_CONFLICT,
                    content = @Content)})//
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDto createBrand(@RequestBody BrandDto brandDto) {
        return brandMapper.toBrandDto(brandService.create(brandDto));
    }

    @Operation(summary = UPDATE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BrandDto updateBrand(@RequestBody BrandDto brandDto) {
        return brandMapper.toBrandDto(brandService.update(brandDto));
    }

}
