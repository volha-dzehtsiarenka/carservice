package com.degtyarenko.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CarCaseDto extends AbstractDto {

    @Size(min = 2, max = 20, message = "Car case should be between 2 and 20 characters")
    private String carCase;

}
