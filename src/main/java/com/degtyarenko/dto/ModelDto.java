package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.degtyarenko.constant.ModelConstant.MODEL_EXAMPLE;
import static com.degtyarenko.constant.SchemaConstant.INT_1;
import static com.degtyarenko.constant.SchemaConstant.INT_2;
import static com.degtyarenko.constant.SchemaConstant.INT_20;
import static com.degtyarenko.constant.SchemaConstant.STRING_1;

/**
 * The type Model dto.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Schema(description = "Model object")
public class ModelDto {

    @Schema(example = STRING_1, minLength = INT_1)
    private Long id;

    @Schema(example = MODEL_EXAMPLE, minLength = INT_1, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String modelName;

    @Schema(example = STRING_1, minLength = INT_1)
    private Long brandId;

}
