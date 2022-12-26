package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description = "Create model object without system info")
public class ModelDto extends AbstractDto {

    @Schema(example = "Quattro", required = true, minLength = 2, maxLength = 20)
    @Size(min = 2, max = 20)
    @NotNull
    private String modelName;

    @Schema(example = "1", required = true, minLength = 1)
    @NotNull
    private Long brandId;

}
