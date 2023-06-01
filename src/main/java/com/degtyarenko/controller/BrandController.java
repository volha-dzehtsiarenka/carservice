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


import static com.degtyarenko.constant.BrandConstant.BRANDS_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.BrandConstant.BRAND_CREATE_SUCCESSFULLY;
import static com.degtyarenko.constant.BrandConstant.BRAND_DELETE_SUCCESSFULLY;
import static com.degtyarenko.constant.BrandConstant.BRAND_FOUND;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_CREATED_CONFLICT;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_CREATED_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_DELETED_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_FOUND;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_FOUND_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.BrandConstant.BRAND_NOT_UPDATE_ILLEGAL_ARGUMENTS;
import static com.degtyarenko.constant.BrandConstant.BRAND_UPDATE_SUCCESSFULLY;
import static com.degtyarenko.constant.BrandConstant.CREATE_NEW_BRAND;
import static com.degtyarenko.constant.BrandConstant.DELETE_BRAND;
import static com.degtyarenko.constant.BrandConstant.FIND_ALL_BRANDS;
import static com.degtyarenko.constant.BrandConstant.FIND_BRAND_BY_ID;
import static com.degtyarenko.constant.BrandConstant.UPDATE_BRAND;
import static com.degtyarenko.constant.StatusConstant.BAD_REQUEST;
import static com.degtyarenko.constant.StatusConstant.DELETED_SUCCESSFUL;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_200;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_201;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_400;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_404;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_500;
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
    private final BrandMapper brandMapper;
    private final KafkaProducerService kafkaProducerService;
    private static final String TOKEN_GRANTED_PAYLOAD = "successfully got token %s";

    @Operation(summary = FIND_ALL_BRANDS, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = FIND_ALL_BRANDS,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRANDS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrandDto>> findAll() {
        return new ResponseEntity<>(brandMapper.toBrandDtoList(brandService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = FIND_BRAND_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_FOUND,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDto> findById(@PathVariable Long id) {
        Brand brandById = brandService.findById(id);
        String tokenGrantedLog = String.format(TOKEN_GRANTED_PAYLOAD, brandById.getBrandName());
        kafkaProducerService.logTokenGrant(tokenGrantedLog);
        return new ResponseEntity<>(brandMapper.toBrandDto(brandById), HttpStatus.OK);
    }

    @Operation(summary = DELETE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
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
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody BrandSaveDto brandDto) {
        Brand createBrand = brandService.create(brandDto);
        return new ResponseEntity<>(brandMapper.toBrandDto(createBrand), HttpStatus.CREATED);
    }

    @Operation(summary = UPDATE_BRAND, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = BRAND_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = BrandDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = BRAND_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = BRAND_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BrandDto> updateBrand(@Valid @RequestBody BrandDto brandDto) {
        Brand updateBrand = brandService.update(brandDto);
        return new ResponseEntity<>(brandMapper.toBrandDto(updateBrand), HttpStatus.OK);
    }

}
