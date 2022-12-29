package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Create car case-model object")
public class CarCaseModelDto extends AbstractDto {

    @Schema(example = "1", minLength = 1)
    @NotNull
    private Long carCaseId;

    @Schema(example = "1", minLength = 1)
    @NotNull
    private Long modelId;

}
