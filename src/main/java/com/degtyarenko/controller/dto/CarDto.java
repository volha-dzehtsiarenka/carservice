package com.degtyarenko.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CarDto extends AbstractDto{

    private BrandDto brand;
    private ModelDto model;
    private BodyTypeDto bodyType;
    private LocalDate date;
}
