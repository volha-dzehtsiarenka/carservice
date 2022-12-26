package com.degtyarenko.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ModelDto extends AbstractDto {

    @Size(min = 2, max = 20)
    private String modelName;

    private Long brandId;

}
