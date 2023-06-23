package com.degtyarenko.controller;

import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.dto.ModelSaveDto;
import com.degtyarenko.entity.Model;
import com.degtyarenko.mappers.ModelMapper;
import com.degtyarenko.service.ModelService;
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

import static com.degtyarenko.constant.ModelConstant.*;
import static com.degtyarenko.constant.StatusConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Model controller.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
@Tag(name = "Model controller")
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @Operation(summary = FIND_ALL_MODEL_CASE, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = ALL_MODELS_FOUND,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = MODELS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ModelDto> findAll() {
        return modelMapper.toModelDtoList(modelService.findAll());
    }

    @Operation(summary = FIND_MODEL_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = MODEL_FOUND,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = MODELS_NOT_FOUND_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ModelDto findById(@PathVariable Long id) {
        Model modelById = modelService.findById(id);
        return modelMapper.toModelDto(modelById);
    }

    @Operation(summary = DELETE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = MODEL_DELETE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = MODEL_NOT_DELETED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        modelService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = MODEL_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = ModelSaveDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_CREATED_CONFLICT,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_400, description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = MODEL_NOT_CREATED_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelDto createModel(@Valid @RequestBody ModelSaveDto modelDto) {
        Model modelCreate = modelService.create(modelDto);
        return modelMapper.toModelDto(modelCreate);
    }

    @Operation(summary = UPDATE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = MODEL_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_FOUND,
                    content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_500, description = MODEL_NOT_UPDATE_ILLEGAL_ARGUMENTS,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ModelDto updateModel(@Valid @RequestBody ModelDto modelDto) {
        Model modelUpdate = modelService.update(modelDto);
        return modelMapper.toModelDto(modelUpdate);
    }

}
