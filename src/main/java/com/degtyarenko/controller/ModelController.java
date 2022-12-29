package com.degtyarenko.controller;

import com.degtyarenko.dto.AbstractDto;
import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.ModelDto;
import com.degtyarenko.service.ModelService;
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
@RequestMapping("/model")
public class ModelController {

    private final ModelService service;

    @Operation(summary = "Find all model case", responses = {
            @ApiResponse(responseCode = "200", description = "All models found",
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = "500", description = "Models not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find model by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Model found",
                    content = @Content(schema = @Schema(implementation = AbstractDto.class))),
            @ApiResponse(responseCode = "404", description = "Model not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Model not found, Illegal Arguments",
                    content = @Content)})
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete model", responses = {
            @ApiResponse(responseCode = "200", description = "Model delete successfully",
                    content = @Content(schema = @Schema(implementation = AbstractDto.class))),
            @ApiResponse(responseCode = "404", description = "Model not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Model not deleted, Illegal Arguments",
                    content = @Content)})
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Deleted successful!", HttpStatus.OK);
    }

    @Operation(summary = "Create new model", responses = {
            @ApiResponse(responseCode = "201", description = "Model create successfully !",
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = "409", description = "Model not created, Conflict",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Model not created, Illegal Arguments",
                    content = @Content)})
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createModel(@Valid @RequestBody ModelDto modelDto) {
        return new ResponseEntity<>(service.create(modelDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update model", responses = {
            @ApiResponse(responseCode = "200", description = "Model update successfully !",
                    content = @Content(schema = @Schema(implementation = ModelDto.class))),
            @ApiResponse(responseCode = "404", description = "Model not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Model not update, Illegal Arguments",
                    content = @Content)})
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateModel(@Valid @RequestBody ModelDto modelDto) {
        return new ResponseEntity<>(service.update(modelDto), HttpStatus.OK);
    }

}
