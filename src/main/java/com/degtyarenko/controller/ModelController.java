package com.degtyarenko.controller;

import com.degtyarenko.dto.ModelDto;
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

import static com.degtyarenko.constant.ModelConstant.ALL_MODELS_FOUND;
import static com.degtyarenko.constant.ModelConstant.CREATE_NEW_MODEL;
import static com.degtyarenko.constant.ModelConstant.DELETE_MODEL;
import static com.degtyarenko.constant.ModelConstant.FIND_ALL_MODEL_CASE;
import static com.degtyarenko.constant.ModelConstant.FIND_MODEL_BY_ID;
import static com.degtyarenko.constant.ModelConstant.MODEL_CREATE_SUCCESSFULLY;
import static com.degtyarenko.constant.ModelConstant.MODEL_DELETE_SUCCESSFULLY;
import static com.degtyarenko.constant.ModelConstant.MODEL_FOUND;
import static com.degtyarenko.constant.ModelConstant.MODEL_NOT_CREATED_CONFLICT;
import static com.degtyarenko.constant.ModelConstant.MODEL_NOT_FOUND;
import static com.degtyarenko.constant.ModelConstant.MODEL_UPDATE_SUCCESSFULLY;
import static com.degtyarenko.constant.ModelConstant.UPDATE_MODEL;
import static com.degtyarenko.constant.StatusConstant.DELETED_SUCCESSFUL;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_200;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_201;
import static com.degtyarenko.constant.StatusConstant.RESPONSE_CODE_404;
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
                    content = @Content(schema = @Schema(implementation = ModelDto.class)))})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ModelDto> findAll() {
        return modelMapper.toModelDtoList(modelService.findAll());
    }

    @Operation(summary = FIND_MODEL_BY_ID, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = MODEL_FOUND,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_FOUND,
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
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Long id) {
        modelService.delete(id);
        return DELETED_SUCCESSFUL;
    }

    @Operation(summary = CREATE_NEW_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_201, description = MODEL_CREATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_CREATED_CONFLICT,
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelDto createModel(@RequestBody ModelDto modelDto) {
        Model modelCreate = modelService.create(modelDto);
        return modelMapper.toModelDto(modelCreate);
    }

    @Operation(summary = UPDATE_MODEL, responses = {
            @ApiResponse(responseCode = RESPONSE_CODE_200, description = MODEL_UPDATE_SUCCESSFULLY,
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_404, description = MODEL_NOT_FOUND,
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ModelDto updateModel(@RequestBody ModelDto modelDto) {
        Model modelUpdate = modelService.update(modelDto);
        return modelMapper.toModelDto(modelUpdate);
    }

}
