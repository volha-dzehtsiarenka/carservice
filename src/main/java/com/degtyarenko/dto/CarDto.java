package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.degtyarenko.constant.CarConstant.COLOR;
import static com.degtyarenko.constant.CarConstant.INT_30;
import static com.degtyarenko.constant.CarConstant.VIN_CODE_EXAMPLE;
import static com.degtyarenko.constant.SchemaConstant.INT_1;
import static com.degtyarenko.constant.SchemaConstant.INT_2;
import static com.degtyarenko.constant.SchemaConstant.INT_20;
import static com.degtyarenko.constant.SchemaConstant.STRING_1;


/**
 * The type Car dto.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Schema(description = "Update car object")
public class CarDto {

    @Schema(example = STRING_1, minLength = INT_1)
    private Long id;

    @Schema(example = STRING_1, minLength = INT_1)
    private Long carCaseModelId;

    @NotNull
    private LocalDate dateOfIssue;

    @Schema(example = VIN_CODE_EXAMPLE, minLength = INT_1, maxLength = INT_30)
    @Size(min = INT_1, max = INT_30)
    @NotNull
    private String vinCode;

    @Schema(example = COLOR, minLength = INT_2, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String color;

}
