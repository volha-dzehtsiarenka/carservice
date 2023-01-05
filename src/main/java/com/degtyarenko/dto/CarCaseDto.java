package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.degtyarenko.utils.Constant.*;


/**
 * The type Car case dto.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Schema(description = "Update car case object")
public class CarCaseDto {

    @Schema(example = STRING_1, minLength = INT_1)
    @NotNull
    private Long id;

    @Schema(example = CAR_CASE_EXAMPLE, minLength = INT_2, maxLength = INT_20)
    @Size(min = INT_2, max = INT_20)
    @NotNull
    private String name;

}
