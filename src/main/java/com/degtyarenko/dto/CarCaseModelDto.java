package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import static com.degtyarenko.constant.SchemaConstant.INT_1;
import static com.degtyarenko.constant.SchemaConstant.STRING_1;

/**
 * The type Car case model dto.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Schema(description = "Car case-model object")
public class CarCaseModelDto {

    @Schema(example = STRING_1, minLength = INT_1)
    private Long id;

    @Schema(example = STRING_1, minLength = INT_1)
    private Long carCaseId;

    @Schema(example = STRING_1, minLength = INT_1)
    private Long modelId;

}
