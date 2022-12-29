package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description = "Create brand object without system info")
public class BrandDto extends AbstractDto {

    @Schema(example = "Toyota", minLength = 2, maxLength = 20)
    @Size(min = 2, max = 20)
    @NotNull
    private String brandName;

}
