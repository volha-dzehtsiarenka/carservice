package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Create car object")
public class CarDto extends AbstractDto {

    @Schema(example = "1", required = true, minLength = 1)
    @NotNull
    private Long carCaseModelId;

    @NotNull
    private LocalDate dateOfIssue;

    @Schema(example = "123123DFFFFF465646FG4", required = true, minLength = 1, maxLength = 30)
    @Size(min = 1, max = 30)
    @NotNull
    private String vinCode;

    @Schema(example = "GREEN", required = true, minLength = 2, maxLength = 20)
    @Size(min = 2, max = 20)
    @NotNull
    private String color;

}
