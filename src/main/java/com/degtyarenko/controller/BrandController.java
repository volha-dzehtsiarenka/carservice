package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.kafka.KafkaProducerService;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.service.BrandService;
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

    private static final String TOKEN_GRANTED_PAYLOAD = "successfully got token %s";
    private final BrandService brandService;
    private final BrandMapper brandMapper;
    private final KafkaProducerService kafkaProducerService;

    @Operation(summary = FIND_ALL_BRANDS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_BRANDS,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRANDS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BrandDto> findAll() {
        return brandMapper.toBrandDtoList(brandService.findAll());
    }

    @Operation(summary = FIND_BRAND_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_FOUND,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BrandDto findById(@PathVariable Long id) {
        Brand brandById = brandService.findById(id);
        String tokenGrantedLog = String.format(TOKEN_GRANTED_PAYLOAD, brandById.getBrandName());
        kafkaProducerService.logTokenGrant(tokenGrantedLog);
        return brandMapper.toBrandDto(brandById);
    }

    @Operation(summary = DELETE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        brandService.delete(id);
        return DELETED_SUCCESSFUL;
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
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDto createBrand(@Valid @RequestBody BrandSaveDto brandDto) {
        Brand createBrand = brandService.create(brandDto);
        return brandMapper.toBrandDto(createBrand);
    }

    @Operation(summary = UPDATE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BrandDto updateBrand(@Valid @RequestBody BrandDto brandDto) {
        Brand updateBrand = brandService.update(brandDto);
        return brandMapper.toBrandDto(updateBrand);
    }

}
