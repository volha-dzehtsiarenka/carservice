package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.degtyarenko.Constant.INT_1;
import static com.degtyarenko.Constant.STRING_1;

/**
 * The type Car case model save dto.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Schema(description = "Create car case-model object")
public class CarCaseModelSaveDto {

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long carCaseId;

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long modelId;

}
