package com.degtyarenko.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class BrandDto extends AbstractDto {

    @Size(min = 2, max = 20)
    private String brandName;

}
