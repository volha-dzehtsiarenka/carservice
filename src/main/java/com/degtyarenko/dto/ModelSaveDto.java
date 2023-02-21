package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.degtyarenko.constant.ModelConstant.MODEL_EXAMPLE;
import static com.degtyarenko.constant.SchemaConstant.*;

/**
 * The type Model save dto.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Schema(description = "Create model object")
public class ModelSaveDto {

    @Schema(example = MODEL_EXAMPLE, minLength = INT_2, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String modelName;

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long brandId;

}
